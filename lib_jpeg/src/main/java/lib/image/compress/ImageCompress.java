package lib.image.compress;

import android.graphics.Bitmap;

/**
 * description: 图片压缩 - 加载SO
 * created by kalu on 2017/11/22 0:29
 */
class ImageCompress {

    static {
        System.loadLibrary("image-compress");
    }

    /**
     * 采用libjpeg压缩图片
     *
     * @param outpath          用哈夫曼压缩后文件保存路径
     * @param bitmap           需要压缩的bitmap图片
     * @param CompressionRatio 质量1-100 1表示最低质量
     * @param isUseHoffman     是否使用哈夫曼编码
     */
    public static native void nativeLibJpegCompress(String outpath, Bitmap bitmap, int CompressionRatio, boolean isUseHoffman, OnImageCompressChangeListener onImageCompressChangeListener);
}