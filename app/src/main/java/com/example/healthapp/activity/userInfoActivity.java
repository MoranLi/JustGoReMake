package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.healthapp.R;
import com.example.healthapp.datatype.weight;
import com.example.healthapp.globalValue;
import com.example.healthapp.sqlInteraction.userRepo;
import com.example.healthapp.sqlInteraction.weightRepo;

import java.util.Date;

public class userInfoActivity extends AppCompatActivity {

    Button password;

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        password = (Button) findViewById(R.id.profile_change_password);
        password.setOnClickListener(changePassword());
        submit = (Button) findViewById(R.id.button4);
        submit.setOnClickListener(submit_change());
    }

    private View.OnClickListener changePassword() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), forgetPasswordActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private View.OnClickListener submit_change(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean height_change;
                boolean weight_change;
                String new_weight = ((EditText) findViewById(R.id.input1)).getText().toString();
                String new_height = ((EditText) findViewById(R.id.input2)).getText().toString();
                if(new_height.length()==0){
                    height_change = false;
                }
                else{
                    height_change = true;
                }
                if(new_weight.length() == 0){
                    weight_change = false;
                }
                else{
                    weight_change = true;
                }
                if(height_change){
                    userRepo.update_height(Double.parseDouble(new_height));
                }
                if(weight_change){
                    com.example.healthapp.datatype.weight w = new weight();
                    w.setDate(new Date().toString());
                    w.setUser_id(globalValue.getCurrentUserId());
                    w.setId(globalValue.getCurrentWeightId());
                    w.setWeight(Double.parseDouble(new_weight));
                    weightRepo.insert(w);
                }
                RadioButton genders = (RadioButton)findViewById( ((RadioGroup)findViewById(R.id.gender_change_group)).getCheckedRadioButtonId());
                userRepo.update_gender(genders.getText().toString());
                Intent unit_intent = new Intent(getItSelf(), mainMenuActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private Activity getItSelf(){ return this; }



}