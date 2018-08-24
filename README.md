[ ![Download](https://api.bintray.com/packages/zhanghang/maven/imagecompress/images/download.svg) ](https://bintray.com/zhanghang/maven/imagecompress/_latestVersion) ![](https://img.shields.io/badge/Build-Passing-green.svg) ![](https://img.shields.io/badge/API%20-14+-green.svg) [ ![](https://img.shields.io/badge/%E4%BD%9C%E8%80%85-%E5%BC%A0%E8%88%AA-red.svg) ](http://www.jianshu.com/u/22a5d2ee8385) ![](https://img.shields.io/badge/%E9%82%AE%E7%AE%B1-153437803@qq.com-red.svg)
```
compile 'lib.image.compress:imagecompress:<latest-version>'
```

#### 预览图片
![image](https://github.com/153437803/ImageCompress/blob/master/Screenrecorder-2018-08-24-15-34-58-511.gif )

#### 适用环境
```
运行项目, 需要自行配置cmake-android-studio编译环境

1.获取压缩后, 清晰度变化不大的图片文件

2.手机相机拍照高清图片, c压缩图片保持清晰度变换不大, 之后上传至服务器
```

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
