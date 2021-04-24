package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.healthapp.DifferentIdsAndUtilities;
import com.example.healthapp.datatype.Experience;
import com.example.healthapp.sql.SqlLiteInterface;

public class ExperienceRepo {
    public static long insert(Context context, Experience experience){
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id",experience.getId());
        values.put("date",experience.getDate());
        values.put("experience",experience.getExperience());
        return db.insert("experience",null,values);
    }
    public static Experience create(String experience){
        Experience e = new Experience();
        e.setId(DifferentIdsAndUtilities.getCurrentExperienceId());
        e.setDate(DifferentIdsAndUtilities.currentDate());
        e.setExperience(experience);
        return e;
    }
}
