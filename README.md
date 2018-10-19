##
## 预览图片
![image](https://github.com/153437803/ImageCompress/blob/master/Screenrecorder-2018-08-24-15-34-58-511.gif )

##
## 适用环境
```
运行项目, 需要自行配置cmake-android-studio编译环境

1.获取压缩后, 清晰度变化不大的图片文件

2.手机相机拍照高清图片, c压缩图片保持清晰度变换不大, 之后上传至服务器
```

##
## 使用方法
```
Bitmap bitmap = ImageUtil.compressImageJava(getResources(), R.mipmap.test, 2000, 2000);

CompressManager.syncCompress(true, 5, folderPath, imageName, bitmap, new OnCompressChangeListener() {
            @Override
            public void onCompressStart() {
                Log.e(TAG, "onCompressStart()");
            }

            @Override
            public void onCompressError(int errorNum, String description) {
                Log.e(TAG, "onCompressError() ==> errorNum = [" + errorNum + "], description = [" + description + "]");
            }

            @Override
            public void onCompressFinish(String filePath) {
                Log.e(TAG, "onCompressFinish() ==> filePath = [" + filePath + "]");
            }
            
            @Override
            public void onCompressChange(final String percent) {
                Log.e(TAG, "onCompressChange ==> percent = "+percent);
            }
        });
```

##
## 编译方法
```
1. android studio 配置 cmake ndk16 编译环境[ndk17废弃armeabi架构]
2. 下载libjpeg[https://github.com/libjpeg-turbo/libjpeg-turbo]
3. android studio新建空工程, 在app主目录下新建jni目录, 将libjpeg放入jni目录
4. android studio app目录右键选择Link C++ Project With Gradle, 添加jni目录CMakeLists.txt文件
5. app moudle下build.gradle文件, 配置cmake编译文件
        ndk {
            abiFilters 'armeabi', 'armeabi-v7a', 'arm64-v8a', 'x86', 'x86_64', 'arm64-v8a', 'mips', 'mips64'
        }
5. ide选项, Build => Make Project,
```
