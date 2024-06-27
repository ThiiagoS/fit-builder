package cruds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import database.DatabaseHelper;

public class TrainingExercise extends DatabaseHelper {

    public TrainingExercise(Context context) {
        super(context);
    }

    public boolean addTrainingExerciseRelation(int trainingId, int exerciseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TE_TRAINING_ID, trainingId);
        values.put(COLUMN_TE_EXERCISE_ID, exerciseId);
        long result = db.insert(TABLE_TRAINING_EXERCISE, null, values);
        return result != -1;
    }

    public Cursor getExercisesForTraining(int trainingId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_EXERCISE + " INNER JOIN " +
                TABLE_TRAINING_EXERCISE + " ON " +
                TABLE_EXERCISE + "." + COLUMN_EXERCISE_ID + " = " +
                TABLE_TRAINING_EXERCISE + "." + COLUMN_TE_EXERCISE_ID + " WHERE " +
                TABLE_TRAINING_EXERCISE + "." + COLUMN_TE_TRAINING_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(trainingId)});
    }

    public Cursor getTrainingsForExercise(int exerciseId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_TRAINING + " INNER JOIN " +
                TABLE_TRAINING_EXERCISE + " ON " +
                TABLE_TRAINING + "." + COLUMN_TRAINING_ID + " = " +
                TABLE_TRAINING_EXERCISE + "." + COLUMN_TE_TRAINING_ID + " WHERE " +
                TABLE_TRAINING_EXERCISE + "." + COLUMN_TE_EXERCISE_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(exerciseId)});
    }
}