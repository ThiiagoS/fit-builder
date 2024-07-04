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

    public boolean addFriend(Integer userFriend_id, String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userFriend_id", userFriend_id);
        values.put("name", name);
        values.put("email", email);
        long result = db.insert(TABLE_FRIENDS, null, values);
        return result != -1;
    }

    public Cursor getAllFriends() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_FRIENDS, null);
    }

    public boolean updateFriend(int id, Integer userFriend_id, String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userFriend_id", userFriend_id);
        values.put("name", name);
        values.put("email", email);
        int result = db.update(TABLE_FRIENDS, values, "id" + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteFriend(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_FRIENDS, "id" + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}