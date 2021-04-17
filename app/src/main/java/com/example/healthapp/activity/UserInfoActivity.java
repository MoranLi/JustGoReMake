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

    private Button submit;

    /**
     * create component
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        submit = (Button) findViewById(R.id.button4);
        submit.setOnClickListener(submit_change());
    }


    /**
     * sumbit change to database
     * @return
     */
    private View.OnClickListener submit_change(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_weight = ((EditText) findViewById(R.id.input1)).getText().toString();
                String new_height = ((EditText) findViewById(R.id.input2)).getText().toString();
                String new_name = ((EditText) findViewById(R.id.input_new_username)).getText().toString();
                String new_sq = ((EditText) findViewById(R.id.input_new_sq)).getText().toString();
                String new_sa = ((EditText) findViewById(R.id.input_new_sa)).getText().toString();
                String new_pass = ((EditText) findViewById(R.id.input_new_password)).getText().toString();
                if(new_height.length()!=0){
                    UserRepo.updateHeight(Double.parseDouble(new_height), getItSelf());
                }
                if(new_weight.length() != 0){
                    Weight w = new Weight();
                    w.setDate(new Date().toString());
                    w.setUserId(GlobalValue.getCurrentUserId());
                    w.setId(GlobalValue.getCurrentWeightId());
                    w.setWeight(Double.parseDouble(new_weight));
                    WeightRepo.insert(w);
                }
                if(new_name.length() > 0){
                    UserRepo.updateName(new_name,getItSelf());
                }
                if(new_sa.length() > 0){
                    UserRepo.updateAnswer(new_sa,getItSelf());
                }
                if(new_sq.length() > 0){
                    UserRepo.updateSq(new_sq,getItSelf());
                }
                if(new_pass.length() > 0){
                    UserRepo.updatePassword(new_pass,getItSelf());
                }
                RadioButton genders = (RadioButton)findViewById( ((RadioGroup)findViewById(R.id.gender_change_group)).getCheckedRadioButtonId());
                UserRepo.updateGender(genders.getText().toString(), getItSelf());
                Intent unit_intent = new Intent(getItSelf(), MainMenuActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private Activity getItSelf(){ return this; }



}