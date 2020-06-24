package lib.kalu.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public final class CompressManager {

    //文件读写错误
    public static final int FILE_ERROR = 893;
    //压缩期间的错误
    public static final int INTERNAL_ERROR = FILE_ERROR + 1;
    //Bitmap格式错误
    public static final int BITMAP_FOMAT_ERROR = INTERNAL_ERROR + 1;
    //Bitmap宽高错误
    public static final int BITMAP_HEIGHT_WIDTH_ERROR = BITMAP_FOMAT_ERROR + 1;


    private static final String TAG = "CompressManager";

    /**
     * 把图片转化为指定的ARGB8888格式
     *
     * @param bit 你想转化图片
     * @return
     */
    public static Bitmap changeARGB8888(Bitmap bit) {
        Log.d("native", "compress of native");
        if (bit.getConfig() != Bitmap.Config.ARGB_8888) {
            Bitmap result;

            result = Bitmap.createBitmap(bit.getWidth(), bit.getHeight(),
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            Rect rect = new Rect(0, 0, bit.getWidth(), bit.getHeight());
            canvas.drawBitmap(bit, null, rect, null);

            return result;


        } else {
            return bit;
        }
    }

    /**
     * 同步质量压缩
     *
     * @param hoffman        是否使用哈夫曼编码
     * @param ratio          质量1-100    1是最低质量
     * @param bitmap         需要压缩的bitmap图片
     * @param nativeCallBack 回调 如果是子线程调用那么回调在子线程
     */
    public static void syncCompress(final boolean hoffman, final int ratio,
                                    final String imageFile,
                                    final Bitmap bitmap, final OnCompressChangeListener nativeCallBack) {


        final File image = new File(imageFile);
        try {

            if (image.isFile()) {
                if (image.exists()) {
                    image.delete();
                }
                image.createNewFile();
            }
            nativecompress(hoffman, bitmap, nativeCallBack, imageFile, ratio);
        } catch (IOException e) {
            Log.e("kalu", e.getMessage(), e);
        }
    }

    public static void asynCompress(final int decodeWidth, final int decodeHeight,
            final boolean hoffman, final int ratio,
            final String imageFile, final OnCompressChangeListener nativeCallBack) {

        new Thread() {
            @Override
            public void run() {
                super.run();

                //第一次采样
                BitmapFactory.Options options = new BitmapFactory.Options();
                //该属性设置为true只会加载图片的边框进来，并不会加载图片具体的像素点
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(imageFile, options);

                //获得原图的宽和高
                int outWidth = options.outWidth;
                int outHeight = options.outHeight;
                //定义缩放比例
                int sampleSize = 1;
                while (outHeight / sampleSize > decodeHeight || outWidth / sampleSize > decodeWidth) {
                    //如果宽高的任意一方的缩放比例没有达到要求，都继续增大缩放比例
                    //sampleSize应该为2的n次幂，如果给sampleSize设置的数字不是2的n次幂，那么系统会就近取值
                    sampleSize *= 2;
                }
                /********************************************************************************************/
                //至此，第一次采样已经结束，我们已经成功的计算出了sampleSize的大小
                /********************************************************************************************/
                //二次采样开始
                //二次采样时我需要将图片加载出来显示，不能只加载图片的框架，因此inJustDecodeBounds属性要设置为false
                options.inJustDecodeBounds = false;
                //设置缩放比例
                options.inSampleSize = sampleSize;
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                Bitmap bitmap = BitmapFactory.decodeFile(imageFile, options);
                Log.e("jiji", "asynCompress ==> bitmap = " + bitmap);

                syncCompress(hoffman, ratio, imageFile, bitmap, nativeCallBack);

                if (null != bitmap) {
                    bitmap.recycle();
                    bitmap = null;
                    Log.e("jiji", "asynCompress ==> bitmap = " + bitmap);
                }
            }
        }.start();
    }

    public static void asynCompress(
            final boolean hoffman, final int ratio,
            final String imageFile,
            final Bitmap bitmap, final OnCompressChangeListener nativeCallBack) {

        new Thread() {
            @Override
            public void run() {
                super.run();
                syncCompress(hoffman, ratio, imageFile, bitmap, nativeCallBack);
            }
        }.start();
    }

    private static void nativecompress(boolean isUseHoffman, Bitmap bitmap, OnCompressChangeListener nativeCallBack, String outpath, int CompressionRatio) {
        Bitmap bitmapBack = bitmap;
        if (bitmapBack == null) {
            // LogUtil.e(TAG, "CompressQC==>>传入参数 bitmap 为空");
            return;
        }
        //校验格式
        if (bitmapBack.getConfig() != Bitmap.Config.ARGB_8888) {
            bitmapBack = changeARGB8888(bitmapBack);
        }
        if (nativeCallBack != null) {
            nativeCallBack.onCompressStart();
        }

        CompressNative.nativeJpeg(outpath, bitmapBack, CompressionRatio, isUseHoffman, nativeCallBack);
    }
}
