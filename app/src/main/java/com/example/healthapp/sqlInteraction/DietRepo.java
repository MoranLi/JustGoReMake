package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.healthapp.GlobalValue;
import com.example.healthapp.datatype.Diet;
import com.example.healthapp.datatype.Food;
import com.example.healthapp.sql.SqlLiteInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

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

    private static String currentDate(){
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

    public static ArrayList<Diet> getDietByRange(Context context, String from, String to){
        String selectQuery = "select * from diet where date between '"
                + from + "' and '" + to + "' and user_id = " + GlobalValue.getCurrentUserId();
        ArrayList<Diet> diets = new ArrayList<>();
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Diet d = new Diet();
                d.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                d.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("user_id"))));
                d.setFoodId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("food_id"))));
                d.setDate(cursor.getString(cursor.getColumnIndex("date")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return diets;
    }

    public static int getMinIntake(Context context){
        String selectQuery = "select food_id, count(food_id) as c from diet where user_id = " + GlobalValue.getCurrentUserId() + " group by food_id ";
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int min = 9999999;
        int min_id = 0;
        if (cursor.moveToFirst()) {
            do {
                int amount = cursor.getInt(cursor.getColumnIndex("c"));
                if(amount < min){
                    min = amount;
                    min_id = cursor.getInt(cursor.getColumnIndex("food_id"));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return min_id;
    }

}
