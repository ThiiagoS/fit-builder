package cruds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import database.DatabaseHelper;

public class Exercise extends DatabaseHelper {

    public Exercise(Context context) {
        super(context);
    }

    public boolean addExercise(String name, String muscleGroup, int series, int repetition, int restTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("muscle_group", muscleGroup);
        values.put("series", series);
        values.put("repetition", repetition);
        values.put("rest_time", restTime);
        long result = db.insert(TABLE_EXERCISE, null, values);
        return result != -1;
    }

    public Cursor getAllExercises() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_EXERCISE, null);
    }

    public boolean updateExercise(int id, String name, String muscleGroup, int series, int repetition, int restTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("muscle_group", muscleGroup);
        values.put("series", series);
        values.put("repetition", repetition);
        values.put("rest_time", restTime);
        int result = db.update(TABLE_EXERCISE, values, "id" + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteExercise(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_EXERCISE, "id" + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

}