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
        values.put("user_id",diet.getUserId());
        values.put("food_id",diet.getFoodId());
        values.put("date",diet.getDate());
        long food_Id = db.insert("diet", null, values);
        return (int) food_Id;
    }

    public static String currentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }


    public static Diet createDiet(int food_id){
        Diet diet = new Diet();
        diet.setId(GlobalValue.getCurrentDietId());
        diet.setUserId(GlobalValue.getCurrentUserId());
        diet.setDate(currentDate());
        diet.setFoodId(food_id);
        return diet;
    }
}
