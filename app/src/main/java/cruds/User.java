package cruds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import database.DatabaseHelper;

public class User extends DatabaseHelper {

    public User(Context context) {
        super(context);
    }

    public boolean addUser(String listName, String userEmail, String userPassword, String userGender,
                           double userWidth, double userHeight, double userSize) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, listName);
        values.put(COLUMN_USER_EMAIL, userEmail);
        values.put(COLUMN_USER_PASSWORD, userPassword);
        values.put(COLUMN_USER_GENDER, userGender);
        values.put(COLUMN_USER_WIDTH, userWidth);
        values.put(COLUMN_USER_HEIGHT, userHeight);
        values.put(COLUMN_USER_SIZE, userSize);
        long result = db.insert(TABLE_USER, null, values);
        return result != -1;
    }


    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USER, null);
    }

    public boolean updateUser(int id, String listName, String userEmail, String userPassword, String userGender,
                              double userWidth, double userHeight, double userSize) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, listName);
        values.put(COLUMN_USER_EMAIL, userEmail);
        values.put(COLUMN_USER_PASSWORD, userPassword);
        values.put(COLUMN_USER_GENDER, userGender);
        values.put(COLUMN_USER_WIDTH, userWidth);
        values.put(COLUMN_USER_HEIGHT, userHeight);
        values.put(COLUMN_USER_SIZE, userSize);
        int result = db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_USER, COLUMN_USER_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}