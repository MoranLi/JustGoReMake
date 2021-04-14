package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.healthapp.datatype.User;
import com.example.healthapp.GlobalValue;
import com.example.healthapp.sql.SqlLiteInterface;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class UserRepo {

    private static User create_admin_user(int id){
        User admin = new User();
        admin.setId(id);
        admin.setName("admin");
        admin.setPassword("admin");
        admin.setHeight((Math.random())*100);
        admin.setGender("F");
        admin.setBirthday("19991231");
        admin.setSecurityQuestion("admin?");
        admin.setSecurityAnswer("admin");
        return admin;
    }
    public static void add_admin_user(Context context){
        insert(context,create_admin_user(0));
    }

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
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        long user_Id = db.insert("user", null, values);
        return (int) user_Id;
    }

    public static int check_user_login(Context context, String input_name,String input_password) {
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

    public static ArrayList<HashMap<String, String>> get_user_list(Context context) {
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
                userList.add(user);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }

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

    public static void update_password(String password, Context context) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id", GlobalValue.getCurrentUserId());
        values.put("password",password);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(GlobalValue.getCurrentUserId()) });
    }

    public static void update_height(Double height, Context context) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id", GlobalValue.getCurrentUserId());
        values.put("height",height);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(GlobalValue.getCurrentUserId()) });
    }

    public static void update_gender(String gender, Context context) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id", GlobalValue.getCurrentUserId());
        values.put("gender",gender);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(GlobalValue.getCurrentUserId()) });
    }

    public static void update_name(String name, Context context) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id", GlobalValue.getCurrentUserId());
        values.put("name",name);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(GlobalValue.getCurrentUserId()) });
    }

    public static void update_sq(String sq, Context context) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id", GlobalValue.getCurrentUserId());
        values.put("question",sq);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(GlobalValue.getCurrentUserId()) });
    }

    public static void update_answer(String sq, Context context) {
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id", GlobalValue.getCurrentUserId());
        values.put("answer",sq);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(GlobalValue.getCurrentUserId()) });
    }

}
