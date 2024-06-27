package cruds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import database.DatabaseHelper;

public class Friends extends DatabaseHelper {

    public Friends(Context context) {
        super(context);
    }

    public boolean addFriend(String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FRIENDS_NAME, name);
        values.put(COLUMN_FRIENDS_EMAIL, email);
        long result = db.insert(TABLE_FRIENDS, null, values);
        return result != -1;
    }

    public Cursor getAllFriends() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_FRIENDS, null);
    }

    public boolean updateFriend(int id, String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FRIENDS_NAME, name);
        values.put(COLUMN_FRIENDS_EMAIL, email);
        int result = db.update(TABLE_FRIENDS, values, COLUMN_FRIENDS_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteFriend(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_FRIENDS, COLUMN_FRIENDS_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}