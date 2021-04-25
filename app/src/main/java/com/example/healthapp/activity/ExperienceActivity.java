package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.healthapp.R;
import com.example.healthapp.datatype.Experience;
import com.example.healthapp.sqlInteraction.ExperienceRepo;

/**
 * share experience
 */
public class ExperienceActivity extends AppCompatActivity {

    /**
     * create and init
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        ((Button)findViewById(R.id.submit_experience)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exp = ((EditText)findViewById(R.id.experience)).getText().toString();
                Experience e = ExperienceRepo.create(exp);
                ExperienceRepo.insert(getApplicationContext(),e);
            }
        });
    }
}