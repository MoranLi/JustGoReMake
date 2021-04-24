package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.healthapp.DifferentIdsAndUtilities;
import com.example.healthapp.datatype.User;
import com.example.healthapp.sql.SqlLiteInterface;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class UserRepo {

    /**
     * create admin
     * @param id
     * @return
     */
    private static User createAdminUser(int id){
        User admin = new User();
        admin.setId(id);
        admin.setName("admin");
        admin.setPassword("admin");
        admin.setHeight((Math.random())*100);
        admin.setGender("F");
        admin.setBirthday("19991231");
        admin.setSecurityQuestion("admin?");
        admin.setSecurityAnswer("admin");
        admin.setTarget(60.0);
        return admin;
    }

    /**\
     * add admin
     * @param context
     */
    public static void addAdminUser(Context context){
        insert(context, createAdminUser(0));
    }

    /**
     * iinsert user
     * @param context
     * @param user
     * @return
     */
    public static int insert(Context context, User user) {
        ContentValues values = new ContentValues();
        values.put("id",user.getId());
        values.put("name",user.getName());
        values.put("password",user.getPassword());
        values.put("height",user.getHeight());
        values.put("gender",user.getGender());
        values.put("birthday",user.getBirthday());
        values.put("question",user.getSecurityQuestion());
        values.put("answer",user.getSecurityAnswer());
        values.put("target",user.getTarget());
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        long userId = db.insert("user", null, values);
        return (int) userId;
    }

    /**
     * check login
     * @param context
     * @param input_name
     * @param input_password
     * @return
     */
    public static int checkUserLogin(Context context, String input_name, String input_password) {
        String selectQuery =  "select * from user where name = '"+input_name+"'";
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String Password = cursor.getString(cursor.getColumnIndex("password"));
                System.out.println(Password);
                if(Password.equals(input_password)){
                    int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                    cursor.close();
                    return id;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return -1;
    }

    /**
     * get all user
     * @param context
     * @return
     */
    public static ArrayList<HashMap<String, String>> getUserList(Context context) {
        String selectQuery =  "select * from user";
        ArrayList<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        Log.d(TAG, "get_default_user_list: "+db.toString());
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("id", cursor.getString(cursor.getColumnIndex("id")));
                user.put("name", cursor.getString(cursor.getColumnIndex("name")));
                user.put("password",cursor.getString(cursor.getColumnIndex("password")));
                user.put("height",cursor.getString(cursor.getColumnIndex("height")));
                user.put("gender",cursor.getString(cursor.getColumnIndex("gender")));
                user.put("birthday",cursor.getString(cursor.getColumnIndex("birthday")));
                user.put("question",cursor.getString(cursor.getColumnIndex("question")));
                user.put("answer", cursor.getString(cursor.getColumnIndex("answer")));
                user.put("target",cursor.getString(cursor.getColumnIndex("target")));
                userList.add(user);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }

    /**
     * get name user
     * @param name
     * @param context
     * @return
     */
    public static String[] getInfoByName(String name, Context context){
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        String selectQuery =  "select * from user where name = \"" + name + "\"";
        String [] datas = {"","",""};
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            datas[0] = cursor.getString(cursor.getColumnIndex("question"));
            datas[1] = cursor.getString(cursor.getColumnIndex("answer"));
            datas[2] = cursor.getString(cursor.getColumnIndex("id"));
            return datas;
        }
        else{
            return null;
        }
    }

    /**
     * get target user
     * @param context
     * @return
     */
    public static String getGoal(Context context){
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        String selectQuery =  "select * from user where id = \"" + DifferentIdsAndUtilities.getCurrentUserId() + "\"";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex("target"));
        }
        else{
            return null;
        }
    }


    /**
     * get id user
     * @param id
     * @param context
     * @return
     */
    public static HashMap<String, String> getInfoById(int id, Context context){
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        String selectQuery =  "select * from user where id = \"" + id + "\"";
        HashMap<String, String> user = new HashMap<>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            user.put("id", cursor.getString(cursor.getColumnIndex("id")));
            user.put("name", cursor.getString(cursor.getColumnIndex("name")));
            user.put("password",cursor.getString(cursor.getColumnIndex("password")));
            user.put("height",cursor.getString(cursor.getColumnIndex("height")));
            user.put("gender",cursor.getString(cursor.getColumnIndex("gender")));
            user.put("birthday",cursor.getString(cursor.getColumnIndex("birthday")));
            user.put("question",cursor.getString(cursor.getColumnIndex("question")));
            user.put("answer", cursor.getString(cursor.getColumnIndex("answer")));
            return user;
        }
        else{
            return null;
        }
    }

    /**
     * change password
     * @param password
     * @param context
     */
    public static void updatePassword(String password, Context context) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id", DifferentIdsAndUtilities.getCurrentUserId());
        values.put("password",password);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(DifferentIdsAndUtilities.getCurrentUserId()) });
    }

    /**
     * change height
     * @param height
     * @param context
     */
    public static void updateHeight(Double height, Context context) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id", DifferentIdsAndUtilities.getCurrentUserId());
        values.put("height",height);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(DifferentIdsAndUtilities.getCurrentUserId()) });
    }

    /**
     * change gender
     * @param gender
     * @param context
     */
    public static void updateGender(String gender, Context context) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id", DifferentIdsAndUtilities.getCurrentUserId());
        values.put("gender",gender);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(DifferentIdsAndUtilities.getCurrentUserId()) });
    }

    /**
     * change name
     * @param name
     * @param context
     */
    public static void updateName(String name, Context context) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id", DifferentIdsAndUtilities.getCurrentUserId());
        values.put("name",name);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(DifferentIdsAndUtilities.getCurrentUserId()) });
    }

    /**
     * change sq
     * @param sq
     * @param context
     */
    public static void updateSq(String sq, Context context) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id", DifferentIdsAndUtilities.getCurrentUserId());
        values.put("question",sq);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(DifferentIdsAndUtilities.getCurrentUserId()) });
    }

    /**
     * change sa
     * @param sq
     * @param context
     */
    public static void updateAnswer(String sq, Context context) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id", DifferentIdsAndUtilities.getCurrentUserId());
        values.put("answer",sq);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(DifferentIdsAndUtilities.getCurrentUserId()) });
    }

    /**
     * set target weigth of user
     * @param target weight
     * @param context
     */
    public static void updateTarget(double target, Context context) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id", DifferentIdsAndUtilities.getCurrentUserId());
        values.put("target",target);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(DifferentIdsAndUtilities.getCurrentUserId()) });
    }

}
