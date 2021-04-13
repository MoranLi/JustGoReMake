package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.healthapp.GlobalValue;
import com.example.healthapp.datatype.Diet;
import com.example.healthapp.sql.SqlLiteInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DietRepo {

    public static int insert(Context context, Diet diet) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id",diet.getId());
        values.put("user_id",diet.getUser_id());
        values.put("food_id",diet.getFood_id());
        values.put("date",diet.getDate());
        values.put("meal_type",diet.getMeal_type().toString());
        long food_Id = db.insert("diet", null, values);
        return (int) food_Id;
    }

    public static String current_date(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }


    public static Diet create_diet(int food_id){
        Diet diet = new Diet();
        diet.setId(GlobalValue.getCurrentDietId());
        GlobalValue.setCurrentDietId(GlobalValue.getCurrentDietId()+1);
        diet.setUser_id(GlobalValue.getCurrentUserId());
        diet.setDate(current_date());
        diet.setFood_id(food_id);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String current_time = sdf.format(cal.getTime());
        if (Integer.parseInt(current_time.substring(0,2))<10){
            diet.setMeal_type('B');
        }
        else if (Integer.parseInt(current_time.substring(0,2)) >17){
            diet.setMeal_type('S');
        }
        else{
            diet.setMeal_type('L');
        }
        return diet;
    }
}
