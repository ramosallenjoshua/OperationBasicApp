package com.example.operationbasicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.FileReader;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.functions.Logistic;

public class InputActivity extends AppCompatActivity {

    Spinner genderSpinner, schoolaffiliationSpinner;
    EditText ageEditText, ecdscoreEditText;
    Button processBtn;
    Handler handler;
    DataSource source;
    Instances trainDataset;
    Logistic logModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        genderSpinner = findViewById(R.id.genderSpinner);
        schoolaffiliationSpinner = findViewById(R.id.schoolaffiliationSpinner);
        ageEditText = findViewById(R.id.ageEditText);
        ecdscoreEditText = findViewById(R.id.ecdEditText);
        processBtn = findViewById(R.id.processBtn);
        /**try {
            System.out.println("hi");
            FileReader fileReader = new FileReader("whatever.txt");

            BufferedReader br = new BufferedReader(fileReader);
            for (String line; (line = br.readLine()) != null;) {
                System.out.print(line);
            }
            br.close();

            source = new DataSource("/com/example/operationbasicapp/model/student_records_arff.arff");

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            trainDataset = source.getDataSet();
        } catch (Exception e) {
            System.out.println(e);
        }
        trainDataset.setClassIndex(trainDataset.numAttributes()-1);

        try {
            logModel = (Logistic) weka.core.SerializationHelper.read("model/logisticModel.model");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(source.getDataSet(0));
        } catch (Exception e) {
            e.printStackTrace();
        }**/

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