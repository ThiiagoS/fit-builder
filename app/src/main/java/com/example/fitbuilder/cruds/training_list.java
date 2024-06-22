package com.example.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TrainingList extends DatabaseHelper {

public boolean addTrainingList(String name) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_TRAINING_LIST_NAME, name);
    long result = db.insert(TABLE_TRAINING_LIST, null, values);
    return result != -1;
}

public Cursor getAllTrainingLists() {
    SQLiteDatabase db = this.getReadableDatabase();
    return db.rawQuery("SELECT * FROM " + TABLE_TRAINING_LIST, null);
}

public boolean updateTrainingList(int id, String name) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_TRAINING_LIST_NAME, name);
    int result = db.update(TABLE_TRAINING_LIST, values, COLUMN_TRAINING_LIST_ID + " = ?", new String[]{String.valueOf(id)});
    return result > 0;
}

public boolean deleteTrainingList(int id) {
    SQLiteDatabase db = this.getWritableDatabase();
    int result = db.delete(TABLE_TRAINING_LIST, COLUMN_TRAINING_LIST_ID + " = ?", new String[]{String.valueOf(id)});
    return result > 0;
}

public boolean addTrainingToTrainingList(int trainingListId, int trainingId) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_TE_TRAINING_LIST_ID, trainingListId);
    values.put(COLUMN_TE_TRAINING_ID, trainingId);
    long result = db.insert(TABLE_TRAINING_TRAINING_LIST, null, values);
    return result != -1;
}

public Cursor getTrainingsForTrainingList(int trainingListId) {
    SQLiteDatabase db = this.getReadableDatabase();
    String query = "SELECT * FROM " + TABLE_TRAINING + " INNER JOIN " +
                   TABLE_TRAINING_TRAINING_LIST + " ON " +
                   TABLE_TRAINING + "." + COLUMN_TRAINING_ID + " = " +
                   TABLE_TRAINING_TRAINING_LIST + "." + COLUMN_TE_TRAINING_ID + " WHERE " +
                   TABLE_TRAINING_TRAINING_LIST + "." + COLUMN_TE_TRAINING_LIST_ID + " = ?";
    return db.rawQuery(query, new String[]{String.valueOf(trainingListId)});
}

public Cursor getTrainingListsForTraining(int trainingId) {
    SQLiteDatabase db = this.getReadableDatabase();
    String query = "SELECT * FROM " + TABLE_TRAINING_LIST + " INNER JOIN " +
                   TABLE_TRAINING_TRAINING_LIST + " ON " +
                   TABLE_TRAINING_LIST + "." + COLUMN_TRAINING_LIST_ID + " = " +
                   TABLE_TRAINING_TRAINING_LIST + "." + COLUMN_TE_TRAINING_LIST_ID + " WHERE " +
                   TABLE_TRAINING_TRAINING_LIST + "." + COLUMN_TE_TRAINING_ID + " = ?";
    return db.rawQuery(query, new String[]{String.valueOf(trainingId)});
}
}
