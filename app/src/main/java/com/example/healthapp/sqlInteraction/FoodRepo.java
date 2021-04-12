package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.healthapp.datatype.Food;
import com.example.healthapp.sql.SqlLiteInterface;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class FoodRepo {
    private static String [] meats = {"beef","pork","mutton","chicken"};
    private static String [] vegetables = {"cabbage","eggplant","cucumber","mushroom"};
    private static String [] fruits = {"apple","pear","peach","berry"};
    private static String [] dairys = {"milk","yogurt","ice cream","cream"};
    private static String [] fats = {"canola oil","corn oil","peanut oil","butter"};
    private static String [] grains= {"wheat","rice","barley","oat"};

    private static boolean initialized = false;

    public static int insert(Context context, Food food) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id",food.getId());
        values.put("user_id",food.getUser_id());
        values.put("category",food.getCategory());
        values.put("name",food.getName());
        values.put("protein",food.getProtein());
        values.put("fat",food.getFat());
        values.put("cholesterol",food.getCholesterol());
        values.put("calories",food.getCalories());
        long food_Id = db.insert("food", null, values);
        return (int) food_Id;
    }

    private static Food cretae_default_meats(int i){
        Food a_food = new Food();
        a_food.setId(i);
        a_food.setName(meats[i]);
        a_food.setCalories(Math.random());
        a_food.setCategory(1);
        a_food.setCholesterol(Math.random());
        a_food.setFat(Math.random());
        a_food.setUser_id(0);
        a_food.setProtein(Math.random());
        return a_food;
    }

    private static Food cretae_default_fruits(int i){
        Food a_food = new Food();
        a_food.setId(i+meats.length);
        a_food.setName(fruits[i]);
        a_food.setCalories(Math.random());
        a_food.setCategory(2);
        a_food.setCholesterol(Math.random());
        a_food.setFat(Math.random());
        a_food.setUser_id(0);
        a_food.setProtein(Math.random());
        return a_food;
    }

    private static Food cretae_default_vegetables(int i){
        Food a_food = new Food();
        a_food.setId(i+meats.length+fruits.length);
        a_food.setName(vegetables[i]);
        a_food.setCalories(Math.random());
        a_food.setCategory(3);
        a_food.setCholesterol(Math.random());
        a_food.setFat(Math.random());
        a_food.setUser_id(0);
        a_food.setProtein(Math.random());
        return a_food;
    }

    private static Food cretae_default_dairys(int i){
        Food a_food = new Food();
        a_food.setId(i+meats.length+fruits.length+vegetables.length);
        a_food.setName(dairys[i]);
        a_food.setCalories(Math.random());
        a_food.setCategory(4);
        a_food.setCholesterol(Math.random());
        a_food.setFat(Math.random());
        a_food.setUser_id(0);
        a_food.setProtein(Math.random());
        return a_food;
    }
    private static Food cretae_default_grains(int i){
        Food a_food = new Food();
        a_food.setId(i+meats.length+fruits.length+vegetables.length+dairys.length);
        a_food.setName(grains[i]);
        a_food.setCalories(Math.random());
        a_food.setCategory(5);
        a_food.setCholesterol(Math.random());
        a_food.setFat(Math.random());
        a_food.setUser_id(0);
        a_food.setProtein(Math.random());
        return a_food;
    }
    private static Food cretae_default_fats(int i){
        Food a_food = new Food();
        a_food.setId(i+meats.length+fruits.length+vegetables.length+dairys.length+grains.length);
        a_food.setName(fats[i]);
        a_food.setCalories(Math.random());
        a_food.setCategory(6);
        a_food.setCholesterol(Math.random());
        a_food.setFat(Math.random());
        a_food.setUser_id(0);
        a_food.setProtein(Math.random());
        return a_food;
    }

    public static void add_default_food(Context context){
        if(initialized){
            return;
        }
        for(int i=0;i<4;i++){
            insert(context,cretae_default_meats(i));
        }
        for(int i=0;i<4;i++){
            insert(context,cretae_default_fruits(i));
        }
        for(int i=0;i<4;i++){
            insert(context,cretae_default_vegetables(i));
        }
        for(int i=0;i<4;i++){
            insert(context,cretae_default_dairys(i));
        }
        for(int i=0;i<4;i++){
            insert(context,cretae_default_grains(i));
        }
        for(int i=0;i<4;i++){
            insert(context,cretae_default_fats(i));
        }
        initialized = true;
    }

    public static ArrayList<HashMap<String, String>> get_default_food_list(Context context) {
        String selectQuery =  "select * from food where user_id = 0";
        ArrayList<HashMap<String, String>> foodList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        Log.d(TAG, "get_default_food_list: "+db.toString());
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> food = new HashMap<String, String>();
                food.put("id", cursor.getString(cursor.getColumnIndex("id")));
                food.put("category",cursor.getString(cursor.getColumnIndex("category")));
                food.put("name", cursor.getString(cursor.getColumnIndex("name")));
                food.put("protein",cursor.getString(cursor.getColumnIndex("protein")));
                food.put("fat",cursor.getString(cursor.getColumnIndex("protein")));
                food.put("calories",cursor.getString(cursor.getColumnIndex("calories")));
                food.put("cholesterol",cursor.getString(cursor.getColumnIndex("cholesterol")));
                foodList.add(food);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return foodList;
    }

    public static String get_food_by_name(Context context, String input_name) {
        String selectQuery =  "select * from food where name == '"+input_name+"'";
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        HashMap<String, String> food = new HashMap<String, String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                food.put("id", cursor.getString(cursor.getColumnIndex("id")));
                food.put("category",cursor.getString(cursor.getColumnIndex("category")));
                food.put("name", cursor.getString(cursor.getColumnIndex("name")));
                food.put("protein",cursor.getString(cursor.getColumnIndex("protein")));
                food.put("fat",cursor.getString(cursor.getColumnIndex("protein")));
                food.put("calories",cursor.getString(cursor.getColumnIndex("calories")));
                food.put("cholesterol",cursor.getString(cursor.getColumnIndex("cholesterol")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return food.toString();
    }
    
}
