package com.example.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class User extends DatabaseHelper {

    public boolean addUser(String listName, String userEmail, String userPassword, String userGender, 
                           double userWidth, double userHeight, double userSize) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LIST_NAME, listName);
        values.put(COLUMN_USER_EMAIL, userEmail);
        values.put(COLUMN_USER_PASSWORD, userPassword);
        values.put(COLUMN_USER_GENDER, userGender);
        values.put(COLUMN_USER_WIDTH, userWidth);
        values.put(COLUMN_USER_HEIGHT, userHeight);
        values.put(COLUMN_USER_SIZE, userSize);
        long result = db.insert(TABLE_LIST, null, values);
        return result != -1;
    }


    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_LIST, null);
    }

    public boolean updateUser(int id, String listName, String userEmail, String userPassword, String userGender, 
                              double userWidth, double userHeight, double userSize) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LIST_NAME, listName);
        values.put(COLUMN_USER_EMAIL, userEmail);
        values.put(COLUMN_USER_PASSWORD, userPassword);
        values.put(COLUMN_USER_GENDER, userGender);
        values.put(COLUMN_USER_WIDTH, userWidth);
        values.put(COLUMN_USER_HEIGHT, userHeight);
        values.put(COLUMN_USER_SIZE, userSize);
        int result = db.update(TABLE_LIST, values, COLUMN_LIST_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_LIST, COLUMN_LIST_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}
