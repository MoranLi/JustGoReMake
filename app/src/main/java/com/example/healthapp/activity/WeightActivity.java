package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthapp.GlobalValue;
import com.example.healthapp.R;
import com.example.healthapp.datatype.Weight;
import com.example.healthapp.sqlInteraction.WeightRepo;

import java.util.Date;

public class WeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        ((Button)findViewById(R.id.submitweight)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_weight = ((EditText) findViewById(R.id.current_weight)).getText().toString();
                if(new_weight.length() != 0){
                    if(Double.parseDouble(new_weight) < 0){
                        Toast.makeText(WeightActivity.this, "Weigth can not be negative", Toast.LENGTH_SHORT).show();
                    }
                    Weight w = new Weight();
                    w.setDate(new Date().toString());
                    w.setUserId(GlobalValue.getCurrentUserId());
                    w.setId(GlobalValue.getCurrentWeightId());
                    w.setWeight(Double.parseDouble(new_weight));
                    WeightRepo.insert(w);
                }
            }
        });
    }
}