package com.example.operationbasicapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    //Added Lines
    TextView resultText, detailedResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Added Lines
        resultText = (TextView) findViewById(R.id.resultText);
        detailedResultText = (TextView) findViewById(R.id.detailedResultText);
        String[] results = {"Outstanding", "Very Satisfactory", "Satisfactory", "Fairly Satisfactory"};
        String[] detailedResults = {"20 (24%)", "26 (31%)", "24 (29%)", "13 (16%)"};

        Intent intent = getIntent();
        String output = intent.getStringExtra("output");
        System.out.println(output);

        resultText.setText(results[convertOutput(output)]);
        detailedResultText.setText(showDetailedResults(convertOutput(output),detailedResults));
        //End of Added Lines
    }

    public String showDetailedResults(int outputCase, String[] detailedResults){
        return "Out of 83 students, " + detailedResults[outputCase] + " had this performance";
    }
    public int convertOutput(String output){
        switch(output){
            case "[1]":
                return 0;
            case "[2]":
                return 1;
            case "[3]":
                return 2;
            default:
                return 3;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}