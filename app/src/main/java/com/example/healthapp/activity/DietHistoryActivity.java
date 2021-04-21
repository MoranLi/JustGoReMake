package com.example.healthapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.healthapp.DifferentIdsAndUtilities;
import com.example.healthapp.R;
import com.example.healthapp.datatype.Diet;
import com.example.healthapp.datatype.Food;
import com.example.healthapp.datatype.Weight;
import com.example.healthapp.sqlInteraction.DietRepo;
import com.example.healthapp.sqlInteraction.FoodRepo;
import com.example.healthapp.sqlInteraction.WeightRepo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DietHistoryActivity extends AppCompatActivity {

    EditText start;
    EditText end;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_history);
        setTableDate("1900-01-01",DifferentIdsAndUtilities.currentDate());
        start = ((EditText)findViewById(R.id.input_start_date));
        end = ((EditText)findViewById(R.id.input_end_date));
        start.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event != null &&
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (event == null || !event.isShiftPressed()) {
                        String s = start.getText().toString();
                        String e = end.getText().toString();
                        if(e.length() <= 0){
                            setTableDate(s,DifferentIdsAndUtilities.currentDate());
                        }
                        else{
                            setTableDate(s,e);
                        }
                        return true; // consume.
                    }
                }
                return false; // pass on to other listeners.
            }
        });
        end.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event != null &&
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (event == null || !event.isShiftPressed()) {
                        String s = start.getText().toString();
                        String e = end.getText().toString();
                        if(s.length() <= 0){
                            setTableDate("1900-01-01",e);
                        }
                        else{
                            setTableDate(s,e);
                        }
                        return true; // consume.
                    }
                }
                return false; // pass on to other listeners.
            }
        });
        ((Button)findViewById(R.id.weight_grpah)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = start.getText().toString();
                String e = end.getText().toString();
                if(s.length() <= 0){
                    s = "1900-01-01";
                }
                if(e.length() <= 0){
                    e = DifferentIdsAndUtilities.currentDate();
                }
                ArrayList<Weight> weights = WeightRepo.getWeightByRange(getApplicationContext(),s,e);
                String [] dates = new String[weights.size()];
                double [] weightChange = new double[weights.size()];
                int c = 0;
                for(Weight w: weights){
                    dates[c] = w.getDate();
                    weightChange[c] = w.getWeight();
                    c++;
                }
                Intent unitIntent = new Intent(getApplicationContext(), WeightGraphActivity.class);
                unitIntent.putExtra("weights",weightChange);
                unitIntent.putExtra("dates",dates);
                startActivity(unitIntent);
            }
        });
        ((Button)findViewById(R.id.calorie_graph)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = start.getText().toString();
                String e = end.getText().toString();
                if(s.length() <= 0){
                    s = "1900-01-01";
                }
                if(e.length() <= 0){
                    e = DifferentIdsAndUtilities.currentDate();
                }
                ArrayList<Diet> weights = DietRepo.getFoodByRange(getApplicationContext(),s,e);
                int c = 0;
                HashMap<String, Double> calData = new HashMap<>();
                for(Diet d : weights){
                    Food f = FoodRepo.getFoodById(getApplicationContext(),d.getFoodId());
                    calData.put(d.getDate(), f.getCalories() + calData.getOrDefault(d.getDate(), 0.0));
                }
                String [] dates = new String[calData.keySet().size()];
                double [] weightChange = new double[calData.keySet().size()];
                for(String date: calData.keySet()){
                    dates[c] = date;
                    c++;
                }
                Arrays.sort(dates);
                c = 0;
                for(String date: dates){
                    weightChange[c] = calData.get(date);
                    c++;
                }
                Intent unitIntent = new Intent(getApplicationContext(), CalorieGraphActivity.class);
                unitIntent.putExtra("weights",weightChange);
                unitIntent.putExtra("dates",dates);
                startActivity(unitIntent);
            }
        });
    }

    public void setTableDate(String start, String end){
        ((TableLayout)findViewById(R.id.dietTableLayout)).removeAllViews();
        ArrayList<Diet> diets = DietRepo.getFoodByRange(this, start, end);
        for(Diet d : diets){
            Food f = FoodRepo.getFoodById(this, d.getFoodId());
            if(f == null){
                continue;
            }
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

            TextView text1 = new TextView(this);
            text1.setText(f.getName());
            text1.setTextSize(30);
            text1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView text2 = new TextView(this);
            text2.setText(d.getDate());
            text2.setTextSize(30);
            text2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            text2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            tableRow.addView(text1);
            tableRow.addView(text2);
            ((TableLayout)findViewById(R.id.dietTableLayout)).addView(tableRow);
        }
    }

}