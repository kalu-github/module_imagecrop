
![image](https://github.com/153437803/ImageCompress/blob/master/Screenshot_2017-11-21-23-27-06-089_com.image.compress.png ) 
![image](https://github.com/153437803/ImageCompress/blob/master/Screenshot_2017-11-21-23-27-06-089_com.image.compress1.png ) 

```
适用场景：

1.获取压缩后, 清晰度变化不大的图片文件
2.手机相机拍照高清图片, c压缩图片保持清晰度变换不大, 之后上传至服务器
```

```
// 获取bitmap
Bitmap bitmap = ImageUtil.compressImageJava(getResources(), R.mipmap.test, 2000, 2000);

// c压缩保存至本地
ImageUtil.syncCompressNative(true, 50, "image.jpg", bitmap, new OnImageCompressChangeListener() {
            @Override
            public void onCompressStart() {
                Log.e(TAG, "onCompressStart()");
            }

            @Override
            public void onCompressError(int errorNum, String description) {
                Log.e(TAG, "onCompressError() ==> errorNum = [" + errorNum + "], description = [" + description + "]");
            }

            @Override
            public void onCompressChange(String errorNum) {
                Log.e(TAG, "onCompressChange() ==> present = " + errorNum);
            }

            @Override
            public void onCompressFinish(String filePath) {
                Log.e(TAG, "onCompressFinish() ==> filePath = [" + filePath + "]");
            }
        });
```
