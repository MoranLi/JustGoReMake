/**
 * The class for all functions needed in forget password page
 */
package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthapp.R;
import com.example.healthapp.globalValue;
import com.example.healthapp.sql.sqlLiteInterface;
import com.example.healthapp.sqlInteraction.userRepo;

public class forgetPasswordActivity extends AppCompatActivity {

    String securityAnswer;

    /**
     * generate basic layout for forget password page
     * @param savedInstanceState constructor
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqlLiteInterface.getInstance(this);
        setContentView(R.layout.activity_forget_password);
        String [] qa = userRepo.getInfoByName(globalValue.getCurrentUserName());
        ((TextView)findViewById(R.id.security_question_show)).setText(qa[0]);
        securityAnswer = qa[1];
        ((Button)findViewById(R.id.changePassword)).setOnClickListener(updatePassword());
    }

    /**
     * function used to update password
     */
    private View.OnClickListener updatePassword (){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = ((EditText)findViewById(R.id.security_answer_enter)).getText().toString();
                String newPass = ((EditText)findViewById(R.id.password_enter)).getText().toString();
                if(!answer.equals(securityAnswer)){
                    Toast.makeText(getApplicationContext(), "Incorrect answer",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(newPass.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please fill in the password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                userRepo.update_password(newPass);
                Intent unit_intent = new Intent(getItSelf(),loginActivity.class);
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