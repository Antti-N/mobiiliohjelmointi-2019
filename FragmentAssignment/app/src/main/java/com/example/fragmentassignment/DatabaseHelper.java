package com.example.fragmentassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHelper";

    // Tiedot
    public static final String TABLE_NAME = "people";
    public static final String COL1 = "Name";
    public static final String COL2 = "Date";

    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }
    // Luodaan taulu kun tulee ylös
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + COL1 + " TEXT," +
                " " + COL2 + " TEXT)";
        db.execSQL(createTable);
    }

    // Dropataan upgradessa taulu
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    // Droppaa taulusta kaiken datan
    public void dropAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME;
        db.execSQL(query);
    }

    // Lisää tauluun rivin
    public boolean addRow(String name, String date) {

        boolean success = true;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, date);

        if (db.insert(TABLE_NAME, null, contentValues) > -1)
            success = false;

        return success;

    }
    // KAikki data taulusta
    public Cursor getData() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);

        return data;
    }
}
