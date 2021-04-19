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
    /**
     * insert
     * @param context
     * @param diet
     * @return
     */
    public static int insert(Context context, ExerciseDaily diet) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id",diet.getId());
        values.put("user_id",diet.getUserId());
        values.put("exercise_id",diet.getExerciseId());
        values.put("date",diet.getDate());
        long foodId = db.insert("exerciseDaily", null, values);
        return (int) foodId;
    }

    /**
     * create obj
     * @param exerciseId
     * @return
     */
    public static ExerciseDaily createExerciseDaily(int exerciseId){
        ExerciseDaily e = new ExerciseDaily();
        e.setExerciseId(exerciseId);
        e.setId(GlobalValue.getCurrentExerciseDailyId());
        e.setUserId(GlobalValue.getCurrentUserId());
        e.setDate(GlobalValue.currentDate());
        return e;
    }
}
