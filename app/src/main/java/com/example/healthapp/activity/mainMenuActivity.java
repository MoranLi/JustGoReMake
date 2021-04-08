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

public class mainMenuActivity extends AppCompatActivity {

    /**
     * basic layout for menu page
     * @param savedInstanceState constructor
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ((Button)findViewById(R.id.go_to_food)).setOnClickListener(addFood());
        ((Button)findViewById(R.id.go_to_exercise)).setOnClickListener(addExercise());
        ((Button)findViewById(R.id.go_to_weight)).setOnClickListener(addWeight());
        ((Button)findViewById(R.id.go_to_recommdation)).setOnClickListener(goRecommdation());
        ((Button)findViewById(R.id.go_to_user_info)).setOnClickListener(changeProfile());
    }

    private View.OnClickListener addFood() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), foodActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private View.OnClickListener addExercise() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), exerciseActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private View.OnClickListener addWeight() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), weightActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private View.OnClickListener goRecommdation() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), recommdationActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private View.OnClickListener changeProfile() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), userInfoActivity.class);
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