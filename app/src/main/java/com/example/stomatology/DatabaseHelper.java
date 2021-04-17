package com.example.stomatology;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "patients";
    private static final String ID = "ID";
    private static final String NAME = "name";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME
                + " TEXT)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, item);
        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        }else{
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + ID + " FROM " + TABLE_NAME + " WHERE " +
                NAME + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateName(String newName, int id, String oldName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + NAME + " = '" + newName + "' WHERE " + NAME + " = '" + id + "'" + " AND " + NAME + " = '" + oldName + "'";
        //logs
        db.execSQL(query);
    }

    public void deleteName(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = '" + id + "' AND " +
                NAME + " = '" + name + "'";
        //Logs
        db.execSQL(query);
    }
}