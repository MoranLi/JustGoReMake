package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.healthapp.GlobalValue;
import com.example.healthapp.datatype.Diet;
import com.example.healthapp.datatype.ExerciseDaily;
import com.example.healthapp.sql.SqlLiteInterface;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExerciseDailyRepo {
    public static int insert(Context context, ExerciseDaily diet) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id",diet.getId());
        values.put("user_id",diet.getUserId());
        values.put("exercise_id",diet.getExerciseId());
        values.put("date",diet.getDate());
        long food_Id = db.insert("exerciseDaily", null, values);
        return (int) food_Id;
    }

    public static String currentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }


    public static ExerciseDaily createExerciseDaily(int exerciseId){
        ExerciseDaily e = new ExerciseDaily();
        e.setExerciseId(exerciseId);
        e.setId(GlobalValue.getCurrentExerciseDailyId());
        e.setUserId(GlobalValue.getCurrentUserId());
        e.setDate(currentDate());
        return e;
    }
}
