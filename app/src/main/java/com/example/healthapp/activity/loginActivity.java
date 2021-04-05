package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthapp.R;
import com.example.healthapp.globalValue;
import com.example.healthapp.sqlInteraction.userRepo;
import com.example.healthapp.sqlInteraction.weightRepo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loginActivity extends AppCompatActivity {

    Button login;

    Button register;

    Button forgetPassword;

    String username;

    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activivty);
        this.deleteDatabase("JustGo");
        /*
        if(current_user.getInfoByName("admin") == null){
            current_user.add_admin_user();
        }
        int past_user = current_user.get_user_num();
        for(int i=1;i<=past_user;i++){
            current_user.delete_by_id(i);
        }
        */
        new userRepo(this);
        new weightRepo(this);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(login());
        register = (Button)findViewById(R.id.signup);
        register.setOnClickListener(signup());
        forgetPassword = (Button)findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(forgetPassword());
        if(globalValue.getCurrentMaxUserId() <= 0){
            globalValue.setCurrentMaxUserId(1);
        }
    }

    private View.OnClickListener login(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pattern input_type = Pattern.compile("^[A-Za-z]*$");
                username = ((TextView)findViewById(R.id.username)).getText().toString();
                password = ((TextView)findViewById(R.id.password)).getText().toString();
                Matcher username_matcher = input_type.matcher(username);
                Matcher password_matcher = input_type.matcher(password);
                if(username.length()<= 0 || password.length()<=0){
                    Toast.makeText(getApplicationContext(), "You must enter the user and the password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if(!username_matcher.matches() || !password_matcher.matches()){
                        Toast.makeText(getApplicationContext(), "Invalid input of username / password",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int id = userRepo.check_user_login(username,password);
                    if (id >= 0) {
                        globalValue.setCurrentUserId(id);
                        Intent unit_intent = new Intent(getItSelf(), mainMenuActivity.class);
                        startActivity(unit_intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid combination of user name and password",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        };
    }

    private View.OnClickListener signup(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(),registerActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private View.OnClickListener forgetPassword(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = ((TextView)findViewById(R.id.username)).getText().toString();
                if (username.length() == 0){
                    return;
                }
                if(userRepo.getInfoByName(username) == null){
                    return;
                }
                globalValue.setCurrentUserName(username);
                globalValue.setCurrentUserId(Integer.parseInt(userRepo.getInfoByName(username)[2]));
                Intent unit_intent = new Intent(getItSelf(),forgetPasswordActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    private Activity getItSelf(){
        return this;
    }

}