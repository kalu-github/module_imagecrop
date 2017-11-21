package lib.image.compress;

/**
 * description: 监听
 * created by kalu on 2017/11/22 0:30
 */
public interface OnImageCompressChangeListener {

    void onCompressStart();

    void onCompressFinish(String filePath);

    void onCompressError(int errorNum, String description);
}