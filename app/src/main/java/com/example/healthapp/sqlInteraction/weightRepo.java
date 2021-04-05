package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.healthapp.datatype.weight;
import com.example.healthapp.sql.sqlLiteInterface;

public class weightRepo {

    private static SQLiteDatabase db;

    public weightRepo(Context context){
        if(db == null) {
            db = new sqlLiteInterface(context).getWritableDatabase();
        }
    }

    public static int insert(weight w){
        ContentValues values = new ContentValues();
        values.put("id",w.getId());
        values.put("user_id",w.getUser_id());
        values.put("date",w.getDate());
        values.put("weight",w.getWeight());
        long weight_Id = db.insert("weight", null, values);
        return (int) weight_Id;
    }

}
