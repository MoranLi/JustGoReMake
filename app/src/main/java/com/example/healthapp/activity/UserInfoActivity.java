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
import com.example.healthapp.datatype.Weight;
import com.example.healthapp.GlobalValue;
import com.example.healthapp.sqlInteraction.UserRepo;
import com.example.healthapp.sqlInteraction.WeightRepo;

import java.util.Date;

public class UserInfoActivity extends AppCompatActivity {

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
                Intent unit_intent = new Intent(getItSelf(), ForgetPasswordActivity.class);
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
                    UserRepo.update_height(Double.parseDouble(new_height), getItSelf());
                }
                if(weight_change){
                    Weight w = new Weight();
                    w.setDate(new Date().toString());
                    w.setUser_id(GlobalValue.getCurrentUserId());
                    w.setId(GlobalValue.getCurrentWeightId());
                    w.setWeight(Double.parseDouble(new_weight));
                    WeightRepo.insert(w);
                }
                RadioButton genders = (RadioButton)findViewById( ((RadioGroup)findViewById(R.id.gender_change_group)).getCheckedRadioButtonId());
                UserRepo.update_gender(genders.getText().toString(), getItSelf());
                Intent unit_intent = new Intent(getItSelf(), MainMenuActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private Activity getItSelf(){ return this; }



}