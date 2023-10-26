package com.example.operationbasicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.Manifest;

public class MainActivity extends AppCompatActivity {

    File fileDir;

    Button beginBtn, mainStatisticsBtn;
    InputStream inputStream;
    String destination;
    OutputStream outputStream;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileDir = new File(getDataDir() + "/model.onnx");
        System.out.println(fileDir);

        if(!fileDir.exists()){
            try {
                inputStream = getAssets().open("model.onnx");
                destination = fileDir.toString();
                System.out.println(destination);

                outputStream = new FileOutputStream(fileDir);

                byte[] buffer = new byte[1024];
                int length;

                while((length = inputStream.read(buffer)) > 0){
                    outputStream.write(buffer, 0, length);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();

                if(fileDir.exists()){
                    System.out.println("File Created");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("File Already Exists");
        }

        beginBtn = findViewById(R.id.beginBtn);
        mainStatisticsBtn = findViewById(R.id.mainStatisticsBtn);

        beginBtn.setOnClickListener(View->{
            Intent intent = new Intent(this, InputActivity.class);
            startActivity(intent);
                });

        mainStatisticsBtn.setOnClickListener(View->{
            Intent intent = new Intent(this, StatisticsActivity.class);
            startActivity(intent);
        });
    }
}