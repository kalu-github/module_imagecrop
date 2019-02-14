##
#### 预览图片
![image](https://github.com/153437803/ImageCompress/blob/master/Screenrecorder-2018-08-24-15-34-58-511.gif )

##
#### jpegturbo生成动态库文件路径修改
```
1. jpegturbo201/CMakeLists.txt => 8-11
2. jpegturbo201/sharedlib/CMakeLists.txt => 10-12
```

##
#### 适用环境
```
运行项目, 需要自行配置cmake-android-studio编译环境

1.获取压缩后, 清晰度变化不大的图片文件

2.手机相机拍照高清图片, c压缩图片保持清晰度变换不大, 之后上传至服务器
```

##
#### 使用方法
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
