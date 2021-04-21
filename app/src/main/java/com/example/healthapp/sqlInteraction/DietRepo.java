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
    
    public static void addDefaultDiet(Context context){
        Diet diet1 = new Diet();
        diet1.setId(DifferentIdsAndUtilities.getCurrentDietId());
        diet1.setUserId(0);
        diet1.setDate("2021-03-03");
        diet1.setFoodId(1);
        insert(context,diet1);
        Diet diet2 = new Diet();
        diet2.setId(DifferentIdsAndUtilities.getCurrentDietId());
        diet2.setUserId(0);
        diet2.setDate("2021-03-04");
        diet2.setFoodId(2);
        insert(context,diet2);
        Diet diet3 = new Diet();
        diet3.setId(DifferentIdsAndUtilities.getCurrentDietId());
        diet3.setUserId(0);
        diet3.setDate("2021-03-05");
        diet3.setFoodId(3);
        insert(context,diet3);
        Diet diet4 = new Diet();
        diet4.setId(DifferentIdsAndUtilities.getCurrentDietId());
        diet4.setUserId(0);
        diet4.setDate("2021-03-06");
        diet4.setFoodId(4);
        insert(context,diet4);
        Diet diet6 = new Diet();
        diet6.setId(DifferentIdsAndUtilities.getCurrentDietId());
        diet6.setUserId(0);
        diet6.setDate("2021-03-07");
        diet6.setFoodId(5);
        insert(context,diet6);
    }

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
    public static ArrayList<Diet> getFoodByRange(Context context, String from, String to){
        String selectQuery = "select * from diet where date between date('"
                + from + "') and date('" + to + "') and user_id = " + DifferentIdsAndUtilities.getCurrentUserId() + " order by date";
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
                diets.add(d);
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
