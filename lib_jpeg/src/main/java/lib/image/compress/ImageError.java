package lib.image.compress;

/**
 * description: 错误码
 * created by kalu on 2017/11/22 0:29
 */
class ImageError {
    //文件读写错误
    public static final int FILE_ERROR = 893;
    //压缩期间的错误
    public static final int INTERNAL_ERROR = FILE_ERROR + 1;
    //Bitmap格式错误
    public static final int BITMAP_FOMAT_ERROR = INTERNAL_ERROR + 1;
    //Bitmap宽高错误
    public static final int BITMAP_HEIGHT_WIDTH_ERROR = BITMAP_FOMAT_ERROR + 1;
}
