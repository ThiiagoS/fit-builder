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

    public boolean addUser(String userName, String userEmail, String userPassword, String userGender,
                           Integer age, String userHeight, String userSize) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", userName);
        values.put("email", userEmail);
        values.put("password", userPassword);
        values.put("gender", userGender);
        values.put("age", age);
        values.put("height", userHeight);
        values.put("size", userSize);
        long result = db.insert(TABLE_USER, null, values);
        return result != -1;
    }


    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USER, null);
    }

    public boolean updateUser(int id, String userName, String userEmail, String userPassword, String userGender,
                              Integer age, String userHeight, String userSize) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", userName);
        values.put("email", userEmail);
        values.put("password", userPassword);
        values.put("gender", userGender);
        values.put("age", age);
        values.put("height", userHeight);
        values.put("size", userSize);
        int result = db.update(TABLE_USER, values, "id" + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_USER, "id" + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}