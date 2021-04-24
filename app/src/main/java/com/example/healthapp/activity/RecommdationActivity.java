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
import com.example.healthapp.sqlInteraction.UserRepo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * recommodation page
 */
public class RecommdationActivity extends AppCompatActivity {

    /**
     * create component
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.healthapp.R.layout.activity_recommdation);
        Double target = Double.parseDouble(UserRepo.getGoal(getApplicationContext()));
        int foodId = 0;
        if(target < 20){
            foodId = 1;
        }
        HashMap<String, Food> foods = FoodRepo.getDefaultFoodList(this);
        HashMap<String, Exercise> exercises = ExerciseRepo.getDefaultExerciseList(this);
        for(Food f: foods.values()){
            if (f.getCategory() == foodId){
                ((TextView)findViewById(R.id.food_recomm)).setText("Your show take more "+ f.getName());
                break;
            }
        }
        Iterator<String> name = exercises.keySet().iterator();
        for(int i = 0; i < new Random().nextInt(12); i ++){
            name.next();
        }
        ((TextView)findViewById(R.id.exercise_recomm)).setText("Your show do more "+ name.next());
    }
}