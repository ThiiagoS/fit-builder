package com.example.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userlist.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_LIST = "list";
    public static final String COLUMN_LIST_ID = "list_id";
    public static final String COLUMN_LIST_NAME = "list_name";
    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_USER_PASSWORD = "user_password";
    public static final String COLUMN_USER_GENDER = "user_gender";
    public static final String COLUMN_USER_WIDTH = "user_width";
    public static final String COLUMN_USER_HEIGHT = "user_height";
    public static final String COLUMN_USER_SIZE = "user_size";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_LIST + " (" +
            COLUMN_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_LIST_NAME + " TEXT, " +
            COLUMN_USER_EMAIL + " TEXT, " +
            COLUMN_USER_PASSWORD + " TEXT, " +
            COLUMN_USER_GENDER + " TEXT, " +
            COLUMN_USER_WIDTH + " REAL, " +
            COLUMN_USER_HEIGHT + " REAL, " +
            COLUMN_USER_SIZE + " REAL);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        onCreate(db);
    }

    // Método para adicionar um novo registro
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

    // Método para obter todos os registros
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_LIST, null);
    }

    // Método para atualizar um registro
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

    // Método para deletar um registro
    public boolean deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_LIST, COLUMN_LIST_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}
