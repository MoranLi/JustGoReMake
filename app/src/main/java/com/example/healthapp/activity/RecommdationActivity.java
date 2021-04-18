package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.healthapp.R;
import com.example.healthapp.datatype.Diet;
import com.example.healthapp.datatype.Exercise;
import com.example.healthapp.datatype.Food;
import com.example.healthapp.sqlInteraction.DietRepo;
import com.example.healthapp.sqlInteraction.ExerciseRepo;
import com.example.healthapp.sqlInteraction.FoodRepo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class RecommdationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.healthapp.R.layout.activity_recommdation);
        int foodId = DietRepo.getMinIntake(this);
        HashMap<String, Food> foods = FoodRepo.getDefaultFoodList(this);
        HashMap<String, Exercise> exercises = ExerciseRepo.getDefaultExerciseList(this);
        for(Food f: foods.values()){
            if (f.getId() == foodId){
                ((TextView)findViewById(R.id.food_recomm)).setText("Your show take more "+ f.getName());
            }
        }
        Iterator<String> name = exercises.keySet().iterator();
        for(int i = 0; i < new Random().nextInt(12); i ++){
            name.next();
        }
        ((TextView)findViewById(R.id.exercise_recomm)).setText("Your show do more "+ name.next());
    }
}