package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fitbuilder.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "user";
    public static final String TABLE_TRAINING = "training";
    public static final String TABLE_EXERCISE = "exercise";
    public static final String TABLE_FRIENDS = "friends";
    public static final String TABLE_FRIENDS_RELATION = "friends_relation";

    private static final String TABLE_USER_CREATE =
            "CREATE TABLE " + TABLE_USER + "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "email TEXT, " +
                    "password TEXT, " +
                    "gender TEXT, " +
                    "age INTEGER, " +
                    "height TEXT, " +
                    "size TEXT);";


    private static final String TABLE_CREATE_TRAINING =
            "CREATE TABLE " + TABLE_TRAINING + "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "user_id INTEGER, " +
                    "days_workout TEXT, " +
                    "type TEXT);";


    private static final String TABLE_CREATE_EXERCISE =
            "CREATE TABLE " + TABLE_EXERCISE + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "muscle_group TEXT, " +
                    "series INTEGER, " +
                    "repetition INTEGER, " +
                    "rest_time INTEGER);";


    private static final String TABLE_CREATE_FRIENDS =
            "CREATE TABLE " + TABLE_FRIENDS + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "userFriend_id INTEGER," +
                    "name TEXT, " +
                    "email TEXT);";

    private static final String TABLE_CREATE_FRIENDS_RELATION =
            "CREATE TABLE " + TABLE_FRIENDS_RELATION + " (" +
                    "user_id INTEGER, friend_id INTEGER, " +
                    "FOREIGN KEY(user_id) REFERENCES " + TABLE_USER + "(id), " +
                    "FOREIGN KEY(friend_id) REFERENCES " + TABLE_FRIENDS + "(id));";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER_CREATE);
        db.execSQL(TABLE_CREATE_TRAINING);
        db.execSQL(TABLE_CREATE_EXERCISE);
        db.execSQL(TABLE_CREATE_FRIENDS);
        db.execSQL(TABLE_CREATE_FRIENDS_RELATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAINING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS_RELATION);
        onCreate(db);
    }

}