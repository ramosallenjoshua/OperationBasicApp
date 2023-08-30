package com.example.operationbasicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class InputActivity extends AppCompatActivity {

    Spinner genderSpinner, schoolaffiliationSpinner;
    EditText ageEditText, ecdscoreEditText;
    Button processBtn;
    Handler handler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        genderSpinner = findViewById(R.id.genderSpinner);
        schoolaffiliationSpinner = findViewById(R.id.schoolaffiliationSpinner);
        ageEditText = findViewById(R.id.ageEditText);
        ecdscoreEditText = findViewById(R.id.ecdEditText);
        processBtn = findViewById(R.id.processBtn);


        ArrayAdapter<CharSequence> genderAdpter = ArrayAdapter.createFromResource(this, R.array.Gender, android.R.layout.simple_spinner_item);
        genderAdpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdpter);

        processBtn.setOnClickListener(View->{
            processBtn.setText("Processing...");

            Intent intent = new Intent(this, ResultActivity.class);

            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    {
                        startActivity(intent);
                        finish();
                    }
                }
            }, 2000);

        });
    }
}