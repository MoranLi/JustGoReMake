package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.healthapp.DifferentIdsAndUtilities;
import com.example.healthapp.datatype.Weight;
import com.example.healthapp.sql.SqlLiteInterface;

public class WeightRepo {

    /**
     * add weight record
     * @param context
     * @param w
     * @return
     */
    public static int insert(Context context, Weight w){
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("id",w.getId());
        values.put("user_id",w.getUserId());
        values.put("date", DifferentIdsAndUtilities.currentDate());
        values.put("weight",w.getWeight());
        long weightId = db.insert("weight", null, values);
        return (int) weightId;
    }

    /**
     * change weight record
     * @param context
     * @param w
     * @param date
     * @return
     */
    public static int update(Context context, Weight w, String date){
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        ContentValues values = new ContentValues();
        values.put("weight",w.getWeight());
        long weightId = db.update("weight", values, "date = ? and user_id = ?", new String[] { date, String.valueOf(DifferentIdsAndUtilities.getCurrentUserId()) });
        return (int) weightId;
    }

    /**
     * exist a weigth record today
     * @param context
     * @return
     */
    public static boolean existToday(Context context){
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        String selectQuery =  "select * from weight where date = date('" + DifferentIdsAndUtilities.currentDate()+"')";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToNext()){
            return true;
        }
        return false;
    }

}
