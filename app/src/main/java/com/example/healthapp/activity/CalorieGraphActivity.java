package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthapp.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class CalorieGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_graph);
        double[] weight = getIntent().getDoubleArrayExtra("weights");
        GraphView graph = (GraphView) findViewById(R.id.calorie_graph_page);
        DataPoint[] points = new DataPoint[weight.length];
        try {
            for (int i = 0; i < weight.length; i++) {
                points[i] = new DataPoint(Math.round(i), weight[i]);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(points);
        graph.addSeries(series);
    }
}