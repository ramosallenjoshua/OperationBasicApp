package com.example.operationbasicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button beginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beginBtn = findViewById(R.id.beginBtn);

        beginBtn.setOnClickListener(View->{
            Intent intent = new Intent(this, InputActivity.class);
            startActivity(intent);
                }
                );
    }
}