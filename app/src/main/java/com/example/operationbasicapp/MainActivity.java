package com.example.operationbasicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    Button beginBtn;
    InputStream inputStream;
    String destination;
    OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            inputStream = getAssets().open("kNN.onnx");
            destination = "/storage/emulated/0/Android/data/com.example.operationbasicapp/files/kNN.onnx";
            System.out.println(destination);

            outputStream = new FileOutputStream(destination);

            byte[] buffer = new byte[1024];
            int length;

            while((length = inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        beginBtn = findViewById(R.id.beginBtn);

        beginBtn.setOnClickListener(View->{
            Intent intent = new Intent(this, InputActivity.class);
            startActivity(intent);
                }
                );
    }
}