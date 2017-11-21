package com.image.compress;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import java.io.IOException;
import java.io.InputStream;

import lib.image.compress.ImageUtil;
import lib.image.compress.OnImageCompressChangeListener;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static final String TAG = "MainActivity";

    public void onClick(View view) {
        iv = (ImageView) findViewById(R.id.iv);

        Bitmap bitmap = ImageUtil.compressImageJava(getResources(), R.mipmap.test, 2000, 2000);
        iv.setImageBitmap(bitmap);

        ImageUtil.syncCompressNative(true, 5, "image.jpg", bitmap, new OnImageCompressChangeListener() {
            @Override
            public void onCompressStart() {
                Log.e(TAG, "onCompressStart()");
            }

            @Override
            public void onCompressError(int errorNum, String description) {
                Log.e(TAG, "onCompressError() ==> errorNum = [" + errorNum + "], description = [" + description + "]");
            }

            @Override
            public void onCompressChange(int errorNum) {
                Log.e(TAG, "onCompressChange() ==> present = " + errorNum);
            }

            @Override
            public void onCompressFinish(String filePath) {
                Log.e(TAG, "onCompressFinish() ==> filePath = [" + filePath + "]");
            }
        });
    }
}
