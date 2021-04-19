package com.example.healthapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.healthapp.DifferentIdsAndUtilities;
import com.example.healthapp.R;
import com.example.healthapp.datatype.Weight;
import com.example.healthapp.sqlInteraction.UserRepo;
import com.example.healthapp.sqlInteraction.WeightRepo;

import java.util.Date;

/**
 * change user info
 */
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
        submit.setOnClickListener(submitChange());
    }


    /**
     * sumbit change to database
     * @return
     */
    private View.OnClickListener submitChange(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newWeight = ((EditText) findViewById(R.id.input1)).getText().toString();
                String newHeight = ((EditText) findViewById(R.id.input2)).getText().toString();
                String newName = ((EditText) findViewById(R.id.input_new_username)).getText().toString();
                String newSq = ((EditText) findViewById(R.id.input_new_sq)).getText().toString();
                String newSa = ((EditText) findViewById(R.id.input_new_sa)).getText().toString();
                String newPass = ((EditText) findViewById(R.id.input_new_password)).getText().toString();
                if(newHeight.length()!=0){
                    UserRepo.updateHeight(Double.parseDouble(newHeight), getApplicationContext());
                }
                if(newWeight.length() != 0){
                    Weight w = new Weight();
                    w.setDate(new Date().toString());
                    w.setUserId(DifferentIdsAndUtilities.getCurrentUserId());
                    w.setId(DifferentIdsAndUtilities.getCurrentWeightId());
                    w.setWeight(Double.parseDouble(newWeight));
                    WeightRepo.insert(getApplicationContext(),w);
                }
                if(newName.length() > 0){
                    UserRepo.updateName(newName,getApplicationContext());
                }
                if(newSa.length() > 0){
                    UserRepo.updateAnswer(newSa,getApplicationContext());
                }
                if(newSq.length() > 0){
                    UserRepo.updateSq(newSq,getApplicationContext());
                }
                if(newPass.length() > 0){
                    UserRepo.updatePassword(newPass,getApplicationContext());
                }
                RadioButton genders = (RadioButton)findViewById( ((RadioGroup)findViewById(R.id.gender_change_group)).getCheckedRadioButtonId());
                UserRepo.updateGender(genders.getText().toString(), getApplicationContext());
                Intent unitIntent = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(unitIntent);
            }
        };
    }
}