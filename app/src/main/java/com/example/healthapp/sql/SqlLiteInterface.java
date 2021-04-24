/**
 * class used for dealing with database
 */
package com.example.healthapp.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * general sql inteface
 */
public class SqlLiteInterface extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "JustGo";

    private static SqlLiteInterface sInstance;

    /**
     * create database interface
     * @param context activity
     * @return
     */
    public static synchronized SqlLiteInterface getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SqlLiteInterface(context);
            clear(sInstance.getWritableDatabase());
        }
        return sInstance;
    }

    /**
     *constructor
     * @param context activity
     */
    private SqlLiteInterface(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     *get database
     * @return database
     */
    public static SQLiteDatabase getDatabase(){
        return sInstance.getWritableDatabase();
    }

    /**
     * initialize the database
     * @param db database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String [] end = dropTable();
        for(int i=0;i<end.length;i++){
            db.execSQL(end[i]);
        }
        String [] start = doCreateTableString();
        for(int i=0;i<start.length;i++){
            db.execSQL(start[i]);
        }
    }

    /**
     * upgrade the database
     * @param db   databse
     * @param oldVersion old version number
     * @param newVersion new version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String [] end = dropTable();
        for(int i=0;i<end.length;i++){
            db.execSQL(end[i]);
        }
    }

    /**
     * clean and initialize the database
     * @param db database
     */
    public static void clear(SQLiteDatabase db) {
        String [] end = dropTable();
        for(int i=0;i<end.length;i++){
            db.execSQL(end[i]);
        }
        String [] start = doCreateTableString();
        for(int i=0;i<start.length;i++){
            db.execSQL(start[i]);
        }
    }

    /**
     * create all the table
     */
    private static String [] doCreateTableString(){
        String [] creating = new String [7];
        creating[0] =  "create table food (id int primary key not null, user_id int not null, category int not null, name char(50) not null, protein numeric not null, fat numeric not null, cholesterol numeric not null, calories numeric not null);";
        creating[1] = "create table user (id int primary key not null, name char(50) not null, password char(15) not null, height numeric not null, gender char(1) not null, birthday varchar(8) not null, question char(50) not null, answer char(50) not null, target numeric);";
        creating[2] = "create table weight (id int primary key not null, user_id int not null, date date not null, weight numeric not null);";
        creating[3] = "create table diet (id id int primary key not null, food_id int not null, user_id int not null, date date not null);";
        creating[4] = "create table exerciseDaily (id int primary key not null, exercise_id int not null, user_id int not null, date date not null);";
        creating[5] = "create table exercise (id int primary key not null, category int not null, name char(20) not null, energy_consumption numeric not null);";
        creating[6] = "create table experience (id int primary key not null, experience text not null, date date not null);";
        return creating;
    }

    /**
     * delete all the table
     */
    private static String [] dropTable(){
        String [] droping = new String [7];
        droping[0] = "drop table if exists food;";
        droping[1] = "drop table if exists user;";
        droping[2] = "drop table if exists weight;";
        droping[3] = "drop table if exists diet;";
        droping[4] = "drop table if exists exerciseDaily;";
        droping[5] = "drop table if exists exercise;";
        droping[6] = "drop table if exists experience;";
        return droping;
    }

}
