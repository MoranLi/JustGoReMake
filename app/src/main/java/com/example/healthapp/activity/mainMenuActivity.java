/**
 * The class for all functions needed in the main page (plan to do in next week)
 */
package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

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
    }

    /**
     * used to navigate to other activity
     */
    private Activity getItSelf(){
        return this;
    }
}