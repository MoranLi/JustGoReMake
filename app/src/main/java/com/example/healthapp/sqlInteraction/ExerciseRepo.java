package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.healthapp.datatype.Exercise;
import com.example.healthapp.sql.SqlLiteInterface;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class ExerciseRepo {
    private static String [] endurance = {"run","walk","dance","arm raise","chair dip","leg raise",
            "balance walk","stand on one foot","tai chi", "yoga","buddy stretch","calf"};

    private static boolean initialized = false;

    /**
     * create a exercise
     * @param i
     * @return
     */
    private static Exercise cretaeDefaultEndurance(int i){
        Exercise ex = new Exercise();
        ex.setId(i);
        ex.setCategory(0);
        ex.setName(endurance[i]);
        ex.setEnergyConsumption(Math.random());
        return ex;
    }

    /**
     * insert
     * @param context
     * @param ex
     * @return
     */
    public static int insert(Context context,  Exercise ex) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id", ex.getId());
        values.put("name", ex.getName());
        values.put("category", ex.getCategory());
        values.put("energy_consumption", ex.getEnergyConsumption());
        long exId = db.insert("exercise", null, values);
        return (int) exId;
    }

    /**
     * add default exercise
     * @param context
     */
    public static void addDefaultExercise(Context context){
        if(initialized){
            return;
        }
        for(int i=0;i<endurance.length;i++){
            insert(context,cretaeDefaultEndurance(i));
        }
        initialized = true;
    }

    /**
     * get all exercise
     * @param context
     * @return
     */
    public static HashMap<String, Exercise> getDefaultExerciseList(Context context) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        String selectQuery =  "select * from exercise";
        HashMap<String, Exercise> exList = new HashMap<>();
        Log.d(TAG, "get_default_exercise_list: "+db.toString());
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
               Exercise e = new Exercise();
                e.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                e.setName(cursor.getString(cursor.getColumnIndex("name")));
                e.setCategory(Integer.parseInt(cursor.getString(cursor.getColumnIndex("category"))));
                e.setEnergyConsumption(Double.parseDouble(cursor.getString(cursor.getColumnIndex("energy_consumption"))));
                exList.put(cursor.getString(cursor.getColumnIndex("name")),e);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return exList;
    }

}
