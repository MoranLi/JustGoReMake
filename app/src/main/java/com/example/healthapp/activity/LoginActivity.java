/**
 * The class for all functions needed in login page
 */
package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthapp.DifferentIdsAndUtilities;
import com.example.healthapp.R;
import com.example.healthapp.sql.SqlLiteInterface;
import com.example.healthapp.sqlInteraction.DietRepo;
import com.example.healthapp.sqlInteraction.UserRepo;
import com.example.healthapp.sqlInteraction.WeightRepo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * login page
 */
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
        WeightRepo.addAdminWeight(this);
        DietRepo.addDefaultDiet(this);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(login());
        register = (Button)findViewById(R.id.signup);
        register.setOnClickListener(signup());
        forgetPassword = (Button)findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(forgetPassword());
        DifferentIdsAndUtilities.getCurrentMaxUserId();
    }

    /**
     * function for login button
     */
    private View.OnClickListener login(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pattern inputType = Pattern.compile("^[A-Za-z]*$");
                username = ((TextView)findViewById(R.id.username)).getText().toString();
                password = ((TextView)findViewById(R.id.password)).getText().toString();
                Matcher usernameMatcher = inputType.matcher(username);
                Matcher passwordMatcher = inputType.matcher(password);
                if(username.length()<= 0 || password.length()<=0){
                    Toast.makeText(getApplicationContext(), "You must enter the user and the password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if(!usernameMatcher.matches() || !passwordMatcher.matches()){
                        Toast.makeText(getApplicationContext(), "Invalid input of username / password",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int id = UserRepo.checkUserLogin(
                            getApplicationContext(),username,password);
                    if (id >= 0) {
                        DifferentIdsAndUtilities.setCurrentUserId(id);
                        DifferentIdsAndUtilities.setCurrentUserName(username);
                        Intent unitIntent = new Intent(getApplicationContext(), MainMenuActivity.class);
                        startActivity(unitIntent);
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
                Intent unitIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(unitIntent);
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
                DifferentIdsAndUtilities.setCurrentUserName(username);
                DifferentIdsAndUtilities.setCurrentUserId(Integer.parseInt(UserRepo.getInfoByName(username, getApplicationContext())[2]));
                Intent unitIntent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(unitIntent);
            }
        };
    }

}