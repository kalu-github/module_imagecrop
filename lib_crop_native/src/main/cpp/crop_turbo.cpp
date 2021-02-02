//
// Created by Oleksii Shliama on 3/13/16.
//

#include <stdio.h>
#include <jni.h>
#include <vector>
#include <android/log.h>
#include <iostream>
#include <iomanip>
#include "com_yalantis_ucrop_task_BitmapCropTask.h"

using namespace std;

#define cimg_display 0
#define cimg_use_jpeg
#define cimg_use_png
#define cimg_use_openmp

#include "CImg.h"

using namespace cimg_library;

#define LOG_TAG "uCrop JNI"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

#define SAVE_FORMAT_JPEG 0
#define SAVE_FORMAT_PNG  1

extern "C"
JNIEXPORT jboolean JNICALL cropCImg
        (JNIEnv *env, jobject obj,
         jstring pathSource, jstring pathResult,
         jint left, jint top, jint width, jint height, jfloat angle, jfloat resizeScale,
         jint format, jint quality,
         jint exifDegrees, jint exifTranslation) {

    LOGD("Crop image with CImg");

    const char *file_source_path = env->GetStringUTFChars(pathSource, 0);
    LOGD("cropCImg => pathSource = %s", file_source_path);

    const char *file_result_path = env->GetStringUTFChars(pathResult, 0);
    LOGD("cropCImg => pathResult = %s", file_result_path);

    try {

        long start = clock() / CLOCKS_PER_SEC;
        LOGD("cropCImg => 读取原始图片开始");
        CImg<unsigned char> img(file_source_path);
        long end = clock() / CLOCKS_PER_SEC;
        LOGD("cropCImg => 读取原始图片完成");
        LOGD("cropCImg => 读取原始图片耗时, %us", end - start);

        const int
                x0 = left, y0 = top,
                x1 = left + width - 1, y1 = top + height - 1;

        LOGD("left %d\ntop: %d", left, top);
        LOGD("width %d\nheight: %d", width, height);
        LOGD("angle %f\nresizeScale: %f", angle, resizeScale);
        LOGD("image size pre: %d x %d", img.width(), img.height());
        LOGD("exifDegrees: %d \nexifTranslation: %d", exifDegrees, exifTranslation);

        // Handle exif. However it is slow, maybe calculate warp field according to exif rotation/translation.
        if (exifDegrees != 0) {
            img.rotate(exifDegrees);
        }
        if (exifTranslation != 1) {
            img.mirror("x");
        }

        const int
                size_x = img.width() * resizeScale, size_y = img.height() * resizeScale,
                size_z = -100, size_c = -100, interpolation_type = 1;

        const unsigned int boundary_conditions = 0;
        const float
                centering_x = 0, centering_y = 0, centering_z = 0, centering_c = 0;
        if (resizeScale != 1) {
            img.resize(size_x, size_y, size_z, size_c, interpolation_type,
                       boundary_conditions,
                       centering_x, centering_y, centering_z, centering_c);
        }

        // Create warp field.
        CImg<float> warp(cimg::abs(x1 - x0 + 1), cimg::abs(y1 - y0 + 1), 1, 2);

        const float
                rad = angle * cimg::PI / 180,
                ca = std::cos(rad), sa = std::sin(rad),
                ux = cimg::abs(img.width() * ca), uy = cimg::abs(img.width() * sa),
                vx = cimg::abs(img.height() * sa), vy = cimg::abs(img.height() * ca),
                w2 = 0.5f * img.width(), h2 = 0.5f * img.height(),
                dw2 = 0.5f * (ux + vx), dh2 = 0.5f * (uy + vy);

        LOGD("cimg_forXY => width = %d", warp.width());
        LOGD("cimg_forXY => height = %d", warp.height());

        int size = warp.width() * warp.height();
        LOGD("cimg_forXY => size = %d", size);

        // 进度
        int process = 0;
        int count = 1;

        LOGD("cropCImg => cimg_forXY开始");
        long start3 = clock() / CLOCKS_PER_SEC;
        cimg_forXY(warp, x, y) {
                const float
                        u = x + x0 - dw2, v = y + y0 - dh2;

                warp(x, y, 0) = w2 + u * ca + v * sa;
                warp(x, y, 1) = h2 - u * sa + v * ca;

                // 进度
                float a = count * 1.0f;
                float b = size * 1.0f;
                int c = (int) (a * 100 / b);
                if (c != process) {
                    process = c;
                    // LOGD("cropCImg => process = %d", process);
                }
                ++count;
            }
        long end3 = clock() / CLOCKS_PER_SEC;
        LOGD("cropCImg => cimg_forXY耗时, %us", end3 - start3);

        LOGD("cropCImg => get_warp开始");
        long start1 = clock() / CLOCKS_PER_SEC;
        img = img.get_warp(warp, 0, 1, 2);
        long end1 = clock() / CLOCKS_PER_SEC;
        LOGD("cropCImg => get_warp耗时, %us", end1 - start1);

        if (format == SAVE_FORMAT_JPEG) {
            LOGD("cropCImg => save_jpeg开始");
            long start2 = clock() / CLOCKS_PER_SEC;
            img.save_jpeg(file_result_path, quality);
            long end2 = clock() / CLOCKS_PER_SEC;
            LOGD("cropCImg => save_jpeg耗时, %us", end2 - start2);
        } else if (format == SAVE_FORMAT_PNG) {
            img.save_png(file_result_path, 0);
        } else {
            img.save(file_result_path);
        }

        ~img;
        env->ReleaseStringUTFChars(pathSource, file_source_path);
        env->ReleaseStringUTFChars(pathResult, file_result_path);

        return true;

    } catch (CImgInstanceException e) {
        env->ThrowNew(env->FindClass("java/lang/OutOfMemoryError"), e.what());
    } catch (CImgIOException e) {
        env->ThrowNew(env->FindClass("java/io/IOException"), e.what());
    }

    return false;
}

JNIEXPORT JNICALL jint JNI_OnLoad(JavaVM *vm, void *reserved){

    JNIEnv *env = NULL;

    if ((*vm).GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }

    // java
    jclass clazz = (*env).FindClass("com/yalantis/ucrop/task/BitmapCropTask");
    static JNINativeMethod method[] = {
            {"cropCImg", "(Ljava/lang/String;Ljava/lang/String;IIIIFFIIII)Z", (void *) cropCImg},
    };

    if ((*env).RegisterNatives(clazz, method, 1) != JNI_OK)
        return JNI_ERR;

    LOGD("cropCImg => JNI_OnLoad 成功");
    return JNI_VERSION_1_6;
}