package com.example.justgo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.justgo.R;
import com.example.justgo.datatype.user;
import com.example.justgo.globalValue;
import com.example.justgo.sqlInteraction.userRepo;

import java.util.ArrayList;

public class forgetPasswordActivity extends AppCompatActivity {

    String securityAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ((TextView)findViewById(R.id.username_show)).setText(globalValue.getCurrentUserName());
        String [] qa = userRepo.getInfoByName(globalValue.getCurrentUserName());
        ((TextView)findViewById(R.id.security_question_show)).setText(qa[0]);
        securityAnswer = qa[1];
        ((Button)findViewById(R.id.changePassword)).setOnClickListener(updatePassword());
    }

    private View.OnClickListener updatePassword (){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = ((EditText)findViewById(R.id.security_answer_enter)).getText().toString();
                String newPass = ((EditText)findViewById(R.id.password_enter)).getText().toString();
                if(!answer.equals(securityAnswer)){
                    return;
                }
                if(newPass.length() == 0){
                    return;
                }
                userRepo.update_password(newPass);
                Intent unit_intent = new Intent(getItSelf(),loginActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private Activity getItSelf(){
        return this;
    }

}