package lib.kalu.image;

import android.graphics.Bitmap;

final class Compress {

    static {
        System.loadLibrary("jni-jpeg");
    }

    /**
     * @param outpath 保存路径
     * @param bitmap  bitmap图片
     * @param ratio   质量1-100 1表示最低质量
     * @param hoffman 是否使用哈夫曼编码
     * @return 1:成功, 0：失败
     */
    public static native int zip(String outpath, Bitmap bitmap, int ratio, boolean hoffman);

    public static native int zip(String outpath, Bitmap bitmap, int ratio, boolean hoffman, OnCompressChangeListener listener);
}