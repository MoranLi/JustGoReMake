package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.healthapp.R;

public class ShowFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food);
        String data = getIntent().getStringExtra("data");
        ((TextView)findViewById(R.id.show_food_info)).setText(data);
        ((Button)findViewById(R.id.show_food_back_main)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unitIntent = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(unitIntent);
            }
        });
    }
}