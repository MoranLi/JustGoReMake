package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.healthapp.DifferentIdsAndUtilities;
import com.example.healthapp.datatype.Diet;
import com.example.healthapp.sql.SqlLiteInterface;

import java.util.ArrayList;

public class DietRepo {

    /**
     * insert new diet data
     * @param context
     * @param diet
     * @return
     */
    public static int insert(Context context, Diet diet) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id",diet.getId());
        values.put("user_id",diet.getUserId());
        values.put("food_id",diet.getFoodId());
        values.put("date",diet.getDate());
        long foodId = db.insert("diet", null, values);
        return (int) foodId;
    }


    /**
     * create diet base on food id
     * @param foodId
     * @return
     */
    public static Diet createDiet(int foodId){
        Diet diet = new Diet();
        diet.setId(DifferentIdsAndUtilities.getCurrentDietId());
        diet.setUserId(DifferentIdsAndUtilities.getCurrentUserId());
        diet.setDate(DifferentIdsAndUtilities.currentDate());
        diet.setFoodId(foodId);
        return diet;
    }

    /**
     * get diet in a time range
     * @param context
     * @param from
     * @param to
     * @return
     */
    public static ArrayList<Diet> getDietByRange(Context context, String from, String to){
        String selectQuery = "select * from diet where date between '"
                + from + "' and '" + to + "' and user_id = " + DifferentIdsAndUtilities.getCurrentUserId();
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

    /**
     * get food id which eat least time
     * @param context
     * @return
     */
    public static int getMinIntake(Context context){
        String selectQuery = "select food_id, count(food_id) as c from diet where user_id = " + DifferentIdsAndUtilities.getCurrentUserId() + " group by food_id ";
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int min = 9999999;
        int minId = 0;
        if (cursor.moveToFirst()) {
            do {
                int amount = cursor.getInt(cursor.getColumnIndex("c"));
                if(amount < min){
                    min = amount;
                    minId = cursor.getInt(cursor.getColumnIndex("food_id"));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return minId;
    }

}
