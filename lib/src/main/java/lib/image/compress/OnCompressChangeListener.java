package lib.image.compress;

public interface OnCompressChangeListener {

    void onCompressChange(String percent);

    void onCompressStart();

    void onCompressFinish(String filePath);

    void onCompressError(int errorNum, String description);
}