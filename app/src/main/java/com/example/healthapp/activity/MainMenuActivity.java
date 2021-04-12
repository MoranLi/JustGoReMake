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

public class MainMenuActivity extends AppCompatActivity {

    /**
     * basic layout for menu page
     * @param savedInstanceState constructor
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                Intent unit_intent = new Intent(getItSelf(), FoodActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private View.OnClickListener addExercise() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), ExperienceActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private View.OnClickListener addWeight() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), WeightActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private View.OnClickListener goRecommdation() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), RecommdationActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private View.OnClickListener changeProfile() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), UserInfoActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    /**
     * used to navigate to other activity
     */
    private Activity getItSelf(){
        return this;
    }
}