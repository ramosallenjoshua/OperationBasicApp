package com.example.operationbasicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OnnxTensorLike;
import ai.onnxruntime.OnnxValue;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;

public class InputActivity extends AppCompatActivity {

    Spinner genderSpinner, schoolaffiliationSpinner;
    EditText ageEditText, ecdscoreEditText;
    Button processBtn;
    Handler handler;
    OrtEnvironment env;
    OrtSession.SessionOptions options;
    OrtSession session;
    float[] inputData;
    OrtSession.Result result;
    String output;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        genderSpinner = findViewById(R.id.genderSpinner);
        schoolaffiliationSpinner = findViewById(R.id.schoolaffiliationSpinner);
        ageEditText = findViewById(R.id.ageEditText);
        ecdscoreEditText = findViewById(R.id.ecdEditText);
        processBtn = findViewById(R.id.processBtn);
        inputData = new float[4];

        for(int i = 0; i < inputData.length; i++){
            inputData[i] = -1.0f;
        }

        ArrayAdapter<CharSequence> genderAdpter = ArrayAdapter.createFromResource(this, R.array.Gender, R.layout.spinner_list);
        genderAdpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdpter);

        ArrayAdapter<CharSequence> schoolAffAdapter = ArrayAdapter.createFromResource(this, R.array.School_Affiliation, R.layout.spinner_list);
        schoolAffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolaffiliationSpinner.setAdapter(schoolAffAdapter);

        env = OrtEnvironment.getEnvironment();
        options = new OrtSession.SessionOptions();

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                System.out.println("Selected: " + parent.getItemAtPosition(position).toString());
                if(position == 1){
                    inputData[0] = 0.0f;
                    System.out.println("Input is 0.0f");
                }else if(position == 2){
                    inputData[0] = 1.0f;
                    System.out.println("Input is 0.1f");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        schoolaffiliationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                System.out.println("Selected: " + parent.getItemAtPosition(position).toString());
                if(position == 1){
                    inputData[2] = 0.0f;
                    System.out.println("Input is 0.0f");
                }else if(position == 2){
                    inputData[2] = 1.0f;
                    System.out.println("Input is 0.1f");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        processBtn.setOnClickListener(View->{

            boolean hasMissingItem = false;

            if(!ageEditText.getText().toString().isEmpty()){
                inputData[1] = Float.parseFloat(ageEditText.getText().toString());
                System.out.println(ageEditText.getText().toString());
            }

            if(!ecdscoreEditText.getText().toString().isEmpty()){
                inputData[3] = Float.parseFloat(ageEditText.getText().toString());
                inputData[3] = Float.parseFloat(ecdscoreEditText.getText().toString());
            }

            for (int i = 0; i < inputData.length; i++){
                if(inputData[i] == -1.0f){
                    hasMissingItem = true;
                }
                System.out.println(inputData[i]);
            }

            if(hasMissingItem == false){
                File modelFile = new File(getDataDir() + "/model.onnx");
                if(modelFile.exists()){
                    processBtn.setText("Processing...");

                    try {
                        session = env.createSession( modelFile.toString(), options);
                        System.out.println(session.getInputInfo());

                        FloatBuffer buffer = FloatBuffer.wrap(inputData);
                        System.out.println(buffer);

                        OnnxTensor onnxTensor = OnnxTensor.createTensor(env,buffer, new long[]{1,4});
                        System.out.println(onnxTensor);

                        Map<String, OnnxTensor> inputs = new HashMap<>();
                        inputs.put("float_input",onnxTensor);

                        System.out.println(inputs);

                        result = session.run(inputs);

                        long[] printResult = (long[]) result.get(0).getValue();

                        output = Arrays.toString(printResult);
                        System.out.println(output);

                    } catch (OrtException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(this, ResultActivity.class);

                    handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            {
                                intent.putExtra("output", output);
                                startActivity(intent);
                            }
                        }
                    }, 2000);
                }else{
                    AlertDialog.Builder fileAlert = new AlertDialog.Builder(this);
                    fileAlert.setMessage("Important files are missing from the device. Please make sure to allow permission to write files to device in order to use this feature.")
                            .setTitle("Important Files Missing")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    AlertDialog alert = fileAlert.create();
                    alert.show();
                }

            }else{
                Toast.makeText(this, "Missing or Unselected items", Toast.LENGTH_SHORT).show();
                hasMissingItem = false;
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        genderSpinner = findViewById(R.id.genderSpinner);
        schoolaffiliationSpinner = findViewById(R.id.schoolaffiliationSpinner);
        ageEditText = findViewById(R.id.ageEditText);
        ecdscoreEditText = findViewById(R.id.ecdEditText);
        processBtn = findViewById(R.id.processBtn);
        inputData = new float[4];

        genderSpinner.setSelection(0);
        schoolaffiliationSpinner.setSelection(0);
        ageEditText.setText("");
        ecdscoreEditText.setText("");
        processBtn.setText("Process");

        for(int i = 0; i < inputData.length; i++){
            inputData[i] = -1.0f;
        }
    }
}