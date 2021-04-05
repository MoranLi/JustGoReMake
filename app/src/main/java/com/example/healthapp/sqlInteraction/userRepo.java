package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.healthapp.datatype.user;
import com.example.healthapp.globalValue;
import com.example.healthapp.sql.sqlLiteInterface;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class userRepo {

    private static SQLiteDatabase db;

    public userRepo(Context context){
        if(db == null) {
            db = new sqlLiteInterface(context).getWritableDatabase();
            create_admin_user(0);
        }
    }

    public static int insert(user user) {
        ContentValues values = new ContentValues();
        values.put("id",user.getId());
        values.put("name",user.getName());
        values.put("password",user.getPassword());
        values.put("height",user.getHeight());
        values.put("gender",user.getGender());
        values.put("birthday",user.getBirthday());
        values.put("question",user.getSecurityQuestion());
        values.put("answer",user.getSecurityAnswer());
        long user_Id = db.insert("user", null, values);
        return (int) user_Id;
    }

    private user create_admin_user(int id){
        user admin = new user();
        admin.setId(id);
        admin.setName("admin");
        admin.setPassword("admina");
        admin.setHeight((Math.random())*100);
        admin.setGender("M");
        admin.setBirthday("19991231");
        admin.setSecurityQuestion("name");
        admin.setSecurityAnswer("adminb");
        return admin;
    }

    public static void delete_by_id(int user_Id) {
        db.delete("user", "id" + "= ?", new String[] { String.valueOf(user_Id) });
    }

    public void add_admin_user(){
        insert(create_admin_user(0));
    }

    public static int check_user_login(String input_name,String input_password) {
        String selectQuery =  "select * from user where name = '"+input_name+"'";
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

    public static ArrayList<HashMap<String, String>> get_user_list() {
        String selectQuery =  "select * from user";
        ArrayList<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();
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

    public static int  get_user_num() {
        String selectQuery =  "select * from user";
        int num = 0;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                num++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return num;
    }

    public static String[] getInfoByName(String name){
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

    public static void update_password (String password) {
        ContentValues values = new ContentValues();
        values.put("id", globalValue.getCurrentUserId());
        values.put("password",password);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(globalValue.getCurrentUserId()) });
    }

    public static void update_height (Double height) {
        ContentValues values = new ContentValues();
        values.put("id", globalValue.getCurrentUserId());
        values.put("height",height);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(globalValue.getCurrentUserId()) });
    }

    public static void update_weight (Double weight) {
        ContentValues values = new ContentValues();
        values.put("id", globalValue.getCurrentUserId());
        values.put("weight",weight);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(globalValue.getCurrentUserId()) });
    }

    public static void update_gender (String gender) {
        ContentValues values = new ContentValues();
        values.put("id", globalValue.getCurrentUserId());
        values.put("gender",gender);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(globalValue.getCurrentUserId()) });
    }
}
