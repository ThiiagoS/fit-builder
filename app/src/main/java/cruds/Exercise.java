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
        values.put(COLUMN_EXERCISE_NAME, name);
        values.put(COLUMN_EXERCISE_MUSCLEGROUP, muscleGroup);
        values.put(COLUMN_EXERCISE_SERIES, series);
        values.put(COLUMN_EXERCISE_REPETITION, repetition);
        values.put(COLUMN_EXERCISE_RESTTIME, restTime);
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
        values.put(COLUMN_EXERCISE_NAME, name);
        values.put(COLUMN_EXERCISE_MUSCLEGROUP, muscleGroup);
        values.put(COLUMN_EXERCISE_SERIES, series);
        values.put(COLUMN_EXERCISE_REPETITION, repetition);
        values.put(COLUMN_EXERCISE_RESTTIME, restTime);
        int result = db.update(TABLE_EXERCISE, values, COLUMN_EXERCISE_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteExercise(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_EXERCISE, COLUMN_EXERCISE_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

}