package com.example.justgo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.justgo.R;
import com.example.justgo.datatype.user;
import com.example.justgo.globalValue;
import com.example.justgo.sqlInteraction.userRepo;

import java.util.ArrayList;

public class registerActivity extends AppCompatActivity {

    EditText name;

    EditText password;

    EditText height;

    RadioGroup gender;

    DatePicker birthday;

    EditText question;

    EditText answer;

    Button submit;

    com.example.justgo.datatype.user user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText)findViewById(R.id.username_textedit);
        password =(EditText)findViewById(R.id.password_register_layout);
        height = (EditText) findViewById(R.id.height_register_layout);
        gender = (RadioGroup)findViewById(R.id.choose_gender);
        birthday = (DatePicker)findViewById(R.id.birthday_Picker);
        question = (EditText)findViewById(R.id.security_question_register_layout);
        answer = (EditText)findViewById(R.id.security_answer_register_layout);
        submit = (Button)findViewById(R.id.submit_user_info);
        submit.setOnClickListener(add_user());
    }

    private View.OnClickListener add_user (){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new user();
                user.setId(globalValue.getCurrentMaxUserId());
                globalValue.setCurrentMaxUserId(globalValue.getCurrentMaxUserId()+1);
                user.setName(name.getText().toString());
                user.setHeight(Double.parseDouble(height.getText().toString()));
                user.setPassword(password.getText().toString());
                int selectedId = gender.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                if(radioButton.getText().toString().equals("male")){
                    user.setGender("M");
                }
                else{
                    user.setGender("F");
                }
                int day = birthday.getDayOfMonth();
                int month = birthday.getMonth() + 1;
                int year = birthday.getYear();
                String Day;
                if(month<10){
                    Day = "0"+String.valueOf(day);
                }
                else{
                    Day = String.valueOf(day);
                }
                String Month;
                if(month<10){
                    Month = "0"+String.valueOf(month);
                }
                else{
                    Month = String.valueOf(month);
                }
                String Year = String.valueOf(year);
                user.setBirthday(Year+Month+Day);
                String questionStr = question.getText().toString();
                String answerStr = answer.getText().toString();
                user.setSecurityQuestion(questionStr);
                user.setSecurityAnswer(answerStr);;
                userRepo.insert(user);
                Intent unit_intent = new Intent(get_self(),mainMenuActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private Context get_self(){
        return registerActivity.this;
    }
}