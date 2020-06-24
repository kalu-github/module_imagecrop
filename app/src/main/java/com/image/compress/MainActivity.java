package com.image.compress;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import lib.kalu.image.CompressManager;
import lib.kalu.image.OnCompressChangeListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        String path = getApplicationContext().getFilesDir().getAbsolutePath();
        String file = "image.jpg";

        File file1 = new File(path + "/" + file);
        if (!file1.exists()) {

            InputStream inputStream = getResources().openRawResource(R.raw.image);
            OutputStream os = null;
            try {
                // 2、保存到临时文件
                // 1K的数据缓冲
                byte[] bs = new byte[1024];
                // 读取到的数据长度
                int len;
                // 输出的文件流保存到本地文件

                File tempFile = new File(path);
                if (!tempFile.exists()) {
                    tempFile.mkdirs();
                }
                os = new FileOutputStream(tempFile.getPath() + File.separator + file);
                // 开始读取
                while ((len = inputStream.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 完毕，关闭所有链接
                try {
                    os.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        CompressManager.asynCompress(4000, 4000, true, 20, path + "/" + file, new OnCompressChangeListener() {

            @Override
            public void onCompressChange(final String percent) {
                Log.e("main", "onCompressChange ==> percent = " + percent);

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
                Log.e("main", "onCompressStart()");
            }

            @Override
            public void onCompressError(int errorNum, String description) {
                Log.e("main", "onCompressError() ==> errorNum = [" + errorNum + "], description = [" + description + "]");
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

                Log.e("main", "onCompressFinish() ==> filePath = [" + filePath + "]");
            }
        });
    }
}
