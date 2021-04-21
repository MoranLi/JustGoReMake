package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.healthapp.datatype.Food;
import com.example.healthapp.sql.SqlLiteInterface;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class FoodRepo {
    private static String [] foodname = {"beef","pork","mutton","chicken","cabbage","eggplant","cucumber","mushroom","apple","pear","peach","berry", "milk","yogurt","ice cream","cream","canola oil","corn oil","peanut oil","butter","wheat","rice","barley","oat"};

    private static boolean initialized = false;

    /**
     * insert food
     * @param context
     * @param food
     * @return
     */
    public static int insert(Context context, Food food) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id",food.getId());
        values.put("user_id",food.getUserId());
        values.put("category",food.getCategory());
        values.put("name",food.getName());
        values.put("protein",food.getProtein());
        values.put("fat",food.getFat());
        values.put("cholesterol",food.getCholesterol());
        values.put("calories",food.getCalories());
        long foodId = db.insert("food", null, values);
        return (int) foodId;
    }

    /**
     * create default foods
     * @param id
     * @param category
     * @return
     */
    private static Food cretaeDefaultFoods(int id, int category){
        Food aFood = new Food();
        aFood.setId(id);
        aFood.setName(foodname[id]);
        aFood.setCalories(Math.random()*1000+1);
        aFood.setCategory(category);
        aFood.setCholesterol(Math.random()*1000+1);
        aFood.setFat(Math.random()*1000+1);
        aFood.setUserId(0);
        aFood.setProtein(Math.random()*1000+1);
        return aFood;
    }

    /**
     * add default foods
     * @param context
     */
    public static void addDefaultFood(Context context){
        if(initialized){
            return;
        }
        for(int i = 0;i<24;i++){
            insert(context, cretaeDefaultFoods(i, i / 4));
        }
        initialized = true;
    }

    /**
     * get a type food
     * @param context
     * @param id
     * @return
     */
    public static Food getFoodById(Context context, int id){
        String selectQuery =  "select * from food where id = "+id;
        HashMap<String, Food> items = getFoodList(context, selectQuery);
        for(Food f: items.values()){
            return f;
        }
        return null;
    }

    /**
     * get a type food
     * @param context
     * @param type
     * @return
     */
    public static HashMap<String, Food> getTypeFoodList(Context context, int type){
        String selectQuery =  "select * from food where category = "+type;
        return getFoodList(context, selectQuery);
    }

    /**
     * get all food
     * @param context
     * @return
     */
    public static HashMap<String, Food> getDefaultFoodList(Context context){
        String selectQuery =  "select * from food";
        return getFoodList(context, selectQuery);
    }

    /**
     * get some food
     * @param context
     * @param selectQuery
     * @return
     */
    public static HashMap<String, Food> getFoodList(Context context, String selectQuery) {
        HashMap<String, Food> foods = new HashMap<>();
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        Log.d(TAG, "get_default_food_list: "+db.toString());
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Food f = new Food();
                f.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                f.setCategory(Integer.parseInt(cursor.getString(cursor.getColumnIndex("category"))));
                f.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("category"))));
                f.setProtein(Double.parseDouble(cursor.getString(cursor.getColumnIndex("protein"))));
                f.setCholesterol(Double.parseDouble(cursor.getString(cursor.getColumnIndex("cholesterol"))));
                f.setFat(Double.parseDouble(cursor.getString(cursor.getColumnIndex("fat"))));
                f.setName(cursor.getString(cursor.getColumnIndex("name")));
                f.setCalories(Double.parseDouble(cursor.getString(cursor.getColumnIndex("calories"))));
                foods.put(f.getName(), f);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return foods;
    }
    
}
