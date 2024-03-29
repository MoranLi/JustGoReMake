/**
 * The class for all function used in sign up page
 */
package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.healthapp.DifferentIdsAndUtilities;
import com.example.healthapp.R;
import com.example.healthapp.datatype.User;
import com.example.healthapp.datatype.Weight;
import com.example.healthapp.sqlInteraction.UserRepo;
import com.example.healthapp.sqlInteraction.WeightRepo;

import java.util.Date;

/**
 * register page
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText name;

    private EditText password;

    private EditText height;

    private EditText weight;

    private RadioGroup gender;

    private DatePicker birthday;

    private EditText question;

    private EditText answer;

    private Button submit;

    private User user;

    /**
     * generate basic layout for the sign up page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText)findViewById(R.id.username_textedit);
        password =(EditText) findViewById(R.id.password_register_layout);
        height = (EditText) findViewById(R.id.height_register_layout);
        weight = (EditText) findViewById(R.id.weight_register_layout);
        gender = (RadioGroup)findViewById(R.id.choose_gender);
        birthday = (DatePicker)findViewById(R.id.birthday_Picker);
        question = (EditText)findViewById(R.id.security_question_register_layout);
        answer = (EditText)findViewById(R.id.security_answer_register_layout);
        submit = (Button)findViewById(R.id.submit_user_info);
        submit.setOnClickListener(addUser());
    }

    /**
     * get basic user information and add to the database
     * @return
     */
    private View.OnClickListener addUser(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                Weight w  = new Weight();
                int userId = DifferentIdsAndUtilities.getCurrentMaxUserId();
                user.setId(userId);
                w.setUserId(userId);
                user.setName(name.getText().toString());
                if(Double.parseDouble(height.getText().toString()) < 0){
                    Toast.makeText(getBaseContext(), "height can not be negative", Toast.LENGTH_SHORT).show();
                    return;
                }
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
                user.setTarget(60.0);
                UserRepo.insert(getApplicationContext(),user);
                if(Double.parseDouble(weight.getText().toString()) < 0){
                    Toast.makeText(getBaseContext(), "weight can not be negative", Toast.LENGTH_SHORT).show();
                    return;
                }
                w.setId(DifferentIdsAndUtilities.getCurrentWeightId());
                w.setDate(new Date().toString());
                w.setWeight(Double.parseDouble(weight.getText().toString()));
                WeightRepo.insert(getApplicationContext(),w);

                Intent unitIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(unitIntent);
            }
        };
    }
}