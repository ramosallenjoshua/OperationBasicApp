package com.example.operationbasicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

    Button beginBtn;
    InputStream inputStream;
    String destination;
    OutputStream outputStream;

    private static final int STORAGE_PERMISSION_CODE = 101; //this

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE); //this

        fileDir = new File(getDataDir() + "/kNN.onnx");
        System.out.println(fileDir);

        if(!fileDir.exists()){
            try {
                inputStream = getAssets().open("kNN.onnx");
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

        beginBtn.setOnClickListener(View->{
            Intent intent = new Intent(this, InputActivity.class);
            startActivity(intent);
                }
                );
    }
    //this
    public void checkPermission(String permission, int requestCode){

        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { permission }, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
        }

    }//Until Here
}