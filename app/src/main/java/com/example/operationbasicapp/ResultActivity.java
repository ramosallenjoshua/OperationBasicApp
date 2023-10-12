package com.example.operationbasicapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    //Added Lines
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Added Lines
        resultText = (TextView) findViewById(R.id.resultText);

        Intent intent = getIntent();
        String output = intent.getStringExtra("output");
        System.out.println(output);
        switch(output){
            case "[1]":
                resultText.setText("Outstanding");
                break;
            case "[2]":
                resultText.setText("Very Satisfactory");
                break;
            case "[3]":
                resultText.setText("Satisfactory");
                break;
            default:
                resultText.setText("Fairly Satisfactory");
                break;
        }


        //End of Added Lines
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}