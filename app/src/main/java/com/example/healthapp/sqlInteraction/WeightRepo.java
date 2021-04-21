package com.example.healthapp.sqlInteraction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.healthapp.DifferentIdsAndUtilities;
import com.example.healthapp.datatype.Diet;
import com.example.healthapp.datatype.Weight;
import com.example.healthapp.sql.SqlLiteInterface;

import java.util.ArrayList;

public class WeightRepo {

    public static void addAdminWeight(Context context){
        Weight w1 = new Weight();
        w1.setId(DifferentIdsAndUtilities.getCurrentWeightId());
        w1.setUserId(0);
        w1.setDate("2021-03-03");
        w1.setWeight(18);
        insert(context,w1);
        Weight w2 = new Weight();
        w2.setId(DifferentIdsAndUtilities.getCurrentWeightId());
        w2.setUserId(0);
        w2.setDate("2021-03-04");
        w2.setWeight(19);
        insert(context,w2);
        Weight w3 = new Weight();
        w3.setId(DifferentIdsAndUtilities.getCurrentWeightId());
        w3.setUserId(0);
        w3.setDate("2021-03-05");
        w3.setWeight(20);
        insert(context,w3);
        Weight w4 = new Weight();
        w4.setId(DifferentIdsAndUtilities.getCurrentWeightId());
        w4.setUserId(0);
        w4.setDate("2021-03-06");
        w4.setWeight(21);
        insert(context,w4);
    }

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

    public static ArrayList<Weight> getWeightByRange(Context context, String from, String to){
        String selectQuery = "select * from weight where date between date('"
                + from + "') and date('" + to + "') and user_id = " + DifferentIdsAndUtilities.getCurrentUserId() + " order by date";
        ArrayList<Weight> diets = new ArrayList<>();
        SQLiteDatabase db = SqlLiteInterface.getInstance(context).getDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Weight d = new Weight();
                d.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                d.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("user_id"))));
                d.setDate(cursor.getString(cursor.getColumnIndex("date")));
                d.setWeight(Double.parseDouble(cursor.getString(cursor.getColumnIndex("weight"))));
                diets.add(d);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return diets;
    }

}
