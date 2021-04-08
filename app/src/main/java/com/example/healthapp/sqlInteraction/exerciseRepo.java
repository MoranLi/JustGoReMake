package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.healthapp.datatype.exercise;
import com.example.healthapp.sql.sqlLiteInterface;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class exerciseRepo {
    private String [] endurance = {"run","walk","dance"};
    private String [] strength = {"arm raise","chair dip","leg raise"};
    private String [] balance = {"balance walk","stand on one foot","tai chi"};
    private String [] flexibility = {"yoga","buddy stretch","calf"};

    private static SQLiteDatabase db = sqlLiteInterface.getDatabase();

    private exercise cretae_default_endurance(int i){
        exercise ex = new exercise();
        ex.setId(i);
        ex.setCategory(0);
        ex.setName(endurance[i]);
        ex.setEnergy_consumption(Math.random());
        return ex;
    }

    private exercise cretae_default_strength(int i){
        exercise ex = new exercise();
        ex.setId(i);
        ex.setCategory(1);
        ex.setName(strength[i-3]);
        ex.setEnergy_consumption(Math.random());
        return ex;
    }

    private exercise cretae_default_balance(int i){
        exercise ex = new exercise();
        ex.setId(i);
        ex.setCategory(2);
        ex.setName(balance[i-6]);
        ex.setEnergy_consumption(Math.random());
        return ex;
    }

    private exercise cretae_default_flexibility(int i){
        exercise ex = new exercise();
        ex.setId(i);
        ex.setCategory(3);
        ex.setName(flexibility[i-9]);
        ex.setEnergy_consumption(Math.random());
        return ex;
    }

    public int insert(exercise ex) {
        ContentValues values = new ContentValues();
        values.put("id", ex.getId());
        values.put("name", ex.getName());
        values.put("category", ex.getCategory());
        values.put("energy_consumption", ex.getEnergy_consumption());
        long ex_id = db.insert("exercise", null, values);
        db.close();
        return (int) ex_id;
    }

    private void add_default_exercise(){
        for(int i=0;i<3;i++){
            insert(cretae_default_endurance(i));
        }
        for(int i=3;i<6;i++){
            insert(cretae_default_strength(i));
        }
        for(int i=6;i<9;i++){
            insert(cretae_default_balance(i));
        }
        for(int i=9;i<12;i++){
            insert(cretae_default_flexibility(i));
        }
    }

    public ArrayList<HashMap<String, String>> get_default_exercise_list() {
        String selectQuery =  "select * from exercise";
        ArrayList<HashMap<String, String>> exList = new ArrayList<HashMap<String, String>>();
        Log.d(TAG, "get_default_exercise_list: "+db.toString());
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> exMap = new HashMap<String, String>();
                exMap.put("id", cursor.getString(cursor.getColumnIndex("id")));
                exMap.put("category",cursor.getString(cursor.getColumnIndex("category")));
                exMap.put("name", cursor.getString(cursor.getColumnIndex("name")));
                exMap.put("energy_consumption",cursor.getString(cursor.getColumnIndex("energy_consumption")));
                exList.add(exMap);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return exList;
    }

}
