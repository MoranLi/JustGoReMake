/**
 * The class for all functions needed in the main page (plan to do in next week)
 */
package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.healthapp.R;
import com.example.healthapp.sqlInteraction.ExerciseRepo;
import com.example.healthapp.sqlInteraction.FoodRepo;

public class MainMenuActivity extends AppCompatActivity {

    /**
     * basic layout for menu page
     * @param savedInstanceState constructor
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FoodRepo.addDefaultFood(getApplicationContext());
        ExerciseRepo.addDefaultExercise(getApplicationContext());
        setContentView(com.example.healthapp.R.layout.activity_main_menu);
        ((Button)findViewById(com.example.healthapp.R.id.go_to_food)).setOnClickListener(addFood());
        ((Button)findViewById(R.id.go_to_experience)).setOnClickListener(addExercise());
        ((Button)findViewById(com.example.healthapp.R.id.go_to_weight)).setOnClickListener(addWeight());
        ((Button)findViewById(com.example.healthapp.R.id.go_to_recommdation)).setOnClickListener(goRecommdation());
        ((Button)findViewById(com.example.healthapp.R.id.go_to_user_info)).setOnClickListener(changeProfile());
    }

    private View.OnClickListener addFood() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getApplicationContext(), FoodActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private View.OnClickListener addExercise() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getApplicationContext(), ExperienceActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private View.OnClickListener addWeight() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getApplicationContext(), WeightActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private View.OnClickListener goRecommdation() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getApplicationContext(), RecommdationActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private View.OnClickListener changeProfile() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getApplicationContext(), UserInfoActivity.class);
                startActivity(unit_intent);
            }
        };
    }

}