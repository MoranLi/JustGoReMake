package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.healthapp.GlobalValue;
import com.example.healthapp.R;
import com.example.healthapp.datatype.Diet;
import com.example.healthapp.datatype.Exercise;
import com.example.healthapp.datatype.ExerciseDaily;
import com.example.healthapp.datatype.Food;
import com.example.healthapp.sqlInteraction.DietRepo;
import com.example.healthapp.sqlInteraction.ExerciseDailyRepo;
import com.example.healthapp.sqlInteraction.ExerciseRepo;
import com.example.healthapp.sqlInteraction.FoodRepo;

import java.util.Date;
import java.util.HashMap;

public class FoodActivity extends AppCompatActivity {
    private static final String TAG = "NormalExpandActivity";

    private HashMap<String, Food> foods;

    private HashMap<String, Exercise> exercises;

    /**
     * create component
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        // init foods
        FoodRepo.addDefaultFood(getItSelf());
        ExerciseRepo.addDefaultExercise(getItSelf());
        foods = FoodRepo.getDefaultFoodList(getItSelf());
        exercises = ExerciseRepo.getDefaultExerciseList(getItSelf());
        Spinner meats = (Spinner)findViewById(R.id.meat_spinner);
        meats.setAdapter(getFood(0));
        Spinner veges = (Spinner)findViewById(R.id.vegetable_spinner);
        veges.setAdapter(getFood(1));
        Spinner fruits = (Spinner)findViewById(R.id.fruit_spinner);
        fruits.setAdapter(getFood(2));
        Spinner dairys = (Spinner)findViewById(R.id.dairy_spinner);
        dairys.setAdapter(getFood(3));
        Spinner fats = (Spinner)findViewById(R.id.fat_spinner);
        fats.setAdapter(getFood(4));
        Spinner grains = (Spinner)findViewById(R.id.grain_spinner);
        grains.setAdapter(getFood(5));
        Spinner activitys = (Spinner)findViewById(R.id.exercise_spinner);
        activitys.setAdapter(getExercise());
        Button submit = (Button)findViewById(R.id.submit_today_diet);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String meat = meats.getSelectedItem().toString();
                String vege = veges.getSelectedItem().toString();
                String fruit = fruits.getSelectedItem().toString();
                String dairy = dairys.getSelectedItem().toString();
                String fat = fats.getSelectedItem().toString();
                String grain = grains.getSelectedItem().toString();
                String exercise = activitys.getSelectedItem().toString();
                String ans = "";
                if(meat != "--"){
                    DietRepo.insert(getItSelf(),DietRepo.createDiet(foods.get(meat).getId()));
                    ans += foods.get(meat) + "\n";
                }
                if(vege != "--"){
                    DietRepo.insert(getItSelf(),DietRepo.createDiet(foods.get(vege).getId()));
                    ans += foods.get(vege) + "\n";
                }
                if(fruit != "--"){
                    DietRepo.insert(getItSelf(),DietRepo.createDiet(foods.get(fruit).getId()));
                    ans += foods.get(fruit) + "\n";
                }
                if(dairy != "--"){
                    DietRepo.insert(getItSelf(),DietRepo.createDiet(foods.get(dairy).getId()));
                    ans += foods.get(dairy) + "\n";
                }
                if(fat != "--"){
                    DietRepo.insert(getItSelf(),DietRepo.createDiet(foods.get(fat).getId()));
                    ans += foods.get(fat) + "\n";
                }
                if(grain != "--"){
                    DietRepo.insert(getItSelf(),DietRepo.createDiet(foods.get(grain).getId()));
                    ans += foods.get(grain) + "\n";
                }
                if(exercise != "--"){
                    ExerciseDailyRepo.insert(getItSelf(),ExerciseDailyRepo.createExerciseDaily(exercises.get(exercise).getId()));
                    ans += exercises.get(exercise) + "\n";
                }
                Intent go_to_confirm = new Intent(getItSelf(),ShowFoodActivity.class);
                go_to_confirm.putExtra("data",ans);
                startActivity(go_to_confirm);
            }
        });

    }

    private ArrayAdapter<String> getFood(int category){
        HashMap<String, Food> food = FoodRepo.getTypeFoodList(getItSelf(),category);
        return getdata(food);
    }

    private ArrayAdapter<String> getExercise(){
        HashMap<String, Exercise> food = ExerciseRepo.getDefaultExerciseList(getItSelf());
        return getdata(food);
    }

    private ArrayAdapter<String> getdata(HashMap data){
        //Creating the ArrayAdapter instance having the country list
        String arr[] = new String[data.size()+1];
        // Copying contents of s to arr[]
        arr[0] = "--";
        System.arraycopy(data.keySet().toArray(), 0, arr, 1, data.size());
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arr);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return aa;
    }

    private Activity getItSelf(){
        return this;
    }
}