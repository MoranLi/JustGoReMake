package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.healthapp.GlobalValue;
import com.example.healthapp.datatype.Exercise;
import com.example.healthapp.datatype.Weight;
import com.example.healthapp.sql.SqlLiteInterface;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class WeightRepo {

    public static int insert(Context context, Weight w){
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id",w.getId());
        values.put("user_id",w.getUserId());
        values.put("date",GlobalValue.currentDate());
        values.put("weight",w.getWeight());
        long weightId = db.insert("weight", null, values);
        return (int) weightId;
    }

    public static int update(Context context, Weight w, String date){
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("weight",w.getWeight());
        long weightId = db.update("weight", values, "date = ? and user_id = ?", new String[] { date, String.valueOf(GlobalValue.getCurrentUserId()) });
        return (int) weightId;
    }

    public static boolean existToday(Context context){
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        String selectQuery =  "select * from weight where date = date('" +GlobalValue.currentDate()+"')";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToNext()){
            return true;
        }
        return false;
    }

}
