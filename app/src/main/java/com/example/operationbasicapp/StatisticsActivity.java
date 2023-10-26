package com.example.operationbasicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {

    PieChart lprOPieChart;
    ArrayList<PieEntry> lprOPieEntries;

    int[] colorClassArray = new int[]{
            Color.parseColor("#57CC6C"),
            Color.parseColor("#6DF37A"),
            Color.parseColor("#ECFA73"),
            Color.parseColor("#FFB341")
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        int[] lprOPieData = new int[]{20, 26, 24, 13};
        String[] lprOPieLabels = new String[]{"Outstanding", "Very Satisfactory", "Satisfactory", "Fairly Satisfactory"};
        lprOPieEntries = pieDataInput(lprOPieData, lprOPieLabels);

        lprOPieChart = findViewById(R.id.lprOPieChart);

        setupPieChart(lprOPieChart, lprOPieEntries, colorClassArray);
    }

    private ArrayList<PieEntry> pieDataInput(int[] dataValues, String[] dataLabels){
        if (dataLabels.length != dataValues.length) {
            System.out.println("Data and Labels does not match");
            return null;
        }
        ArrayList<PieEntry> dataVals = new ArrayList<>();

        for(int i = 0; i < dataValues.length; i++){
            dataVals.add(new PieEntry(dataValues[i], dataLabels[i]));
        }
        return dataVals;
    }

    private void setupPieChart(PieChart pieChart,ArrayList<PieEntry> pieEntries, int[] colorClassArray){
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setColors(colorClassArray);
        pieDataSet.setValueTextSize(12f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getLegend().setTextSize(12f);
        pieChart.getLegend().setWordWrapEnabled(true);
        pieChart.getLegend().setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}