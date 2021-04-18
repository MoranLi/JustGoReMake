/**
 * The class for all functions needed in login page
 */
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
import com.example.healthapp.GlobalValue;
import com.example.healthapp.sql.SqlLiteInterface;
import com.example.healthapp.sqlInteraction.UserRepo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private Button login;

    private Button register;

    private Button forgetPassword;

    private String username;

    private String password;

    /**
     * basic layout for login page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activivty);
        SqlLiteInterface.getInstance(this);
        UserRepo.addAdminUser(this);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(login());
        register = (Button)findViewById(R.id.signup);
        register.setOnClickListener(signup());
        forgetPassword = (Button)findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(forgetPassword());
        GlobalValue.getCurrentMaxUserId();
    }

    /**
     * function for login button
     */
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
                    int id = UserRepo.checkUserLogin(
                            getApplicationContext(),username,password);
                    if (id >= 0) {
                        GlobalValue.setCurrentUserId(id);
                        GlobalValue.setCurrentUserName(username);
                        Intent unit_intent = new Intent(getApplicationContext(), MainMenuActivity.class);
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

    /**
     * function for signup button
     */
    private View.OnClickListener signup(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(unit_intent);
            }
        };
    }

    /**
     * function for forget password button
     */
    private View.OnClickListener forgetPassword(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = ((TextView)findViewById(R.id.username)).getText().toString();
                if (username.length() == 0){
                    return;
                }
                if(UserRepo.getInfoByName(username, getApplicationContext()) == null){
                    return;
                }
                GlobalValue.setCurrentUserName(username);
                GlobalValue.setCurrentUserId(Integer.parseInt(UserRepo.getInfoByName(username, getApplicationContext())[2]));
                Intent unit_intent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(unit_intent);
            }
        };
    }

}