package com.example.healthapp.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sqlLiteInterface extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "JustGo";

    private static sqlLiteInterface sInstance;

    public static synchronized sqlLiteInterface getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new sqlLiteInterface(context);
        }
        return sInstance;
    }

    private sqlLiteInterface (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteDatabase getDatabase(){
        return sInstance.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String [] end = drop_table();
        for(int i=0;i<end.length;i++){
            db.execSQL(end[i]);
        }
        String [] start = do_create_table_string();
        for(int i=0;i<start.length;i++){
            db.execSQL(start[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String [] end = drop_table();
        for(int i=0;i<end.length;i++){
            db.execSQL(end[i]);
        }
    }

    private String [] do_create_table_string(){
        String [] creating = new String [6];
        creating[0] =  "create table food (id int primary key not null, user_id int not null, category int not null, name char(50) not null, protein numeric not null, fat numeric not null, cholesterol numeric not null, calories numeric not null);";
        creating[1] = "create table user (id int primary key not null, name char(50) not null, password char(15) not null, height numeric not null, gender char(1) not null, birthday varchar(8) not null, question char(50) not null, answer char(50) not null);";
        creating[2] = "create table weight (id int primary key not null, user_id int not null, date date not null, weight numeric not null);";
        creating[3] = "create table diet (id id int primary key not null, food_id int not null, user_id int not null, date date not null, meal_type char(1) not null);";
        creating[4] = "create table exercise_daily (id int primary key not null, exercise_id int not null, user_id int not null, time datetime not null, duration numeric not null);";
        creating[5] = "create table exercise (id int primary key not null, category int not null, name char(20) not null, energy_consumption numeric not null);";
        return creating;
    }

    private String [] drop_table(){
        String [] droping = new String [6];
        droping[0] = "drop table if exists food;";
        droping[1] = "drop table if exists user;";
        droping[2] = "drop table if exists weight;";
        droping[3] = "drop table if exists diet;";
        droping[4] = "drop table if exists Exercise_daily;";
        droping[5] = "drop table if exists exercise;";
        return droping;
    }
}
