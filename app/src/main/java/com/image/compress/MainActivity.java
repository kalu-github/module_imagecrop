package com.image.compress;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

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

        final String folderPath = Environment.getExternalStorageDirectory().getPath() + "//";
        CompressManager.asynCompress(getResources(), R.mipmap.compress, 2000, 2000, true, 20, folderPath, "compress.jpg", new OnCompressChangeListener() {

            @Override
            public void onCompressChange(final String percent) {
                Log.e(TAG, "onCompressChange ==> percent = " + percent);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        TextView text = (TextView) findViewById(R.id.value);
                        text.setText("压缩进度: " + percent);
                    }
                });
            }

            @Override
            public void onCompressStart() {
                Log.e(TAG, "onCompressStart()");
            }

            @Override
            public void onCompressError(int errorNum, String description) {
                Log.e(TAG, "onCompressError() ==> errorNum = [" + errorNum + "], description = [" + description + "]");
            }

            @Override
            public void onCompressFinish(final String filePath) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageView iv = (ImageView) findViewById(R.id.iv);
                        iv.setImageURI(Uri.fromFile(new File(filePath)));
                    }
                });

                Log.e(TAG, "onCompressFinish() ==> filePath = [" + filePath + "]");
            }
        });
    }
}
