package lib.image.compress;

import android.graphics.Bitmap;

final class CompressNative {

    static {
        System.loadLibrary("image-compress");
    }

    /**
     * 采用libjpeg压缩图片
     *
     * @param outpath          用哈夫曼压缩后文件保存路径
     * @param bitmap           需要压缩的bitmap图片
     * @param ratio 质量1-100 1表示最低质量
     * @param hoffman     是否使用哈夫曼编码
     */
    public static native void nativeLibJpegCompress(String outpath, Bitmap bitmap, int ratio, boolean hoffman, OnCompressChangeListener nativeCallBack);
}