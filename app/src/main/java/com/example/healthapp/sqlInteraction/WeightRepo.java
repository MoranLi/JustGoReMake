package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.healthapp.datatype.Weight;
import com.example.healthapp.sql.SqlLiteInterface;

public class WeightRepo {

    private static SQLiteDatabase db = SqlLiteInterface.getDatabase();

    public static int insert(Weight w){
        ContentValues values = new ContentValues();
        values.put("id",w.getId());
        values.put("user_id",w.getUser_id());
        values.put("date",w.getDate());
        values.put("weight",w.getWeight());
        long weight_Id = db.insert("weight", null, values);
        return (int) weight_Id;
    }

}
