package cruds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import database.DatabaseHelper;

public class Training extends DatabaseHelper {

    public Training(Context context) {
        super(context);
    }

    public boolean addTraining(String name, int userId, String daysWorkout, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TRAINING_NAME, name);
        values.put(COLUMN_TRAINING_USER_ID, userId);
        values.put(COLUMN_TRAINING_DAYS_WEEK, daysWorkout);
        values.put(COLUMN_TRAINING_TYPE, type);
        long result = db.insert(TABLE_TRAINING, null, values);
        return result != -1;
    }

    public Cursor getAllTrainings() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_TRAINING, null);
    }

    public boolean updateTraining(int id, String name, int userId, String daysWorkout, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TRAINING_NAME, name);
        values.put(COLUMN_TRAINING_USER_ID, userId);
        values.put(COLUMN_TRAINING_DAYS_WEEK, daysWorkout);
        values.put(COLUMN_TRAINING_TYPE, type);
        int result = db.update(TABLE_TRAINING, values, COLUMN_TRAINING_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteTraining(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_TRAINING, COLUMN_TRAINING_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}
