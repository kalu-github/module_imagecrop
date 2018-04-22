package com.image.compress;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import lib.image.compress.CompressManager;
import lib.image.compress.OnCompressChangeListener;

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

        final String folderPath = Environment.getExternalStorageDirectory().getPath() + "//";
        CompressManager.syncCompress(true, 5, folderPath, "image.jpg", bitmap, new OnCompressChangeListener() {
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
        });
    }
}
