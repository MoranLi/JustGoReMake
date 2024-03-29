package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthapp.DifferentIdsAndUtilities;
import com.example.healthapp.R;
import com.example.healthapp.datatype.Weight;
import com.example.healthapp.sqlInteraction.UserRepo;
import com.example.healthapp.sqlInteraction.WeightRepo;

/**
 * change weight ans set goal
 */
public class WeightActivity extends AppCompatActivity {

    /**
     * create
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        ((Button)findViewById(R.id.submitweight)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newWeight = ((EditText) findViewById(R.id.current_weight)).getText().toString();
                if(newWeight.length() != 0){
                    if(Double.parseDouble(newWeight) < 0){
                        Toast.makeText(WeightActivity.this, "Weigth can not be negative", Toast.LENGTH_SHORT).show();
                    }
                    Weight w = new Weight();
                    w.setUserId(DifferentIdsAndUtilities.getCurrentUserId());
                    w.setId(DifferentIdsAndUtilities.getCurrentWeightId());
                    w.setDate(DifferentIdsAndUtilities.currentDate());
                    w.setWeight(Double.parseDouble(newWeight));
                    if(!WeightRepo.existToday(getApplicationContext())){
                        WeightRepo.insert(getApplicationContext(),w);
                    }
                    else{
                        WeightRepo.update(getApplicationContext(),w, DifferentIdsAndUtilities.currentDate());
                    }

                }
                String targetWeight  = ((EditText)findViewById(R.id.goal_of_weight)).getText().toString();
                if(targetWeight.length() > 0){
                    if(Double.parseDouble(targetWeight) <= 0){
                        Toast.makeText(WeightActivity.this, "Weight can not be negative", Toast.LENGTH_SHORT).show();
                    }
                    UserRepo.updateTarget(Double.parseDouble(targetWeight),getApplicationContext());
                }
            }
        });
    }
}