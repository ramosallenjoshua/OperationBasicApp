package com.example.operationbasicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
        finish();
    }
}