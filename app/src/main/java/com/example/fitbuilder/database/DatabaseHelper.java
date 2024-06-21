package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fitbuilder.db";
    private static final int DATABASE_VERSION = 1;

    // nome da tabela e das colunas do usuario
    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_GENDER = "gender";
    public static final String COLUMN_USER_WIDTH = "width";
    public static final String COLUMN_USER_HEIGHT = "height";
    public static final String COLUMN_USER_SIZE = "size";

    // nome da tabela e das colunas do plano de treino
    public static final String TABLE_TRAINING = "training";
    public static final String COLUMN_TRAINING_ID = "id";
    public static final String COLUMN_TRAINING_NAME = "name";
    public static final String COLUMN_TRAINING_USER_ID = "user_id";
    public static final String COLUMN_TRAINING_DW = "days_workout";
    public static final String COLUMN_TRAINING_TYPE = "type";

    // nome da tabela e das colunas do exercicio
    public static final String TABLE_EXERCISE = "exercicio";
    public static final String COLUMN_EXERCISE_ID = "id";
    public static final String COLUMN_EXERCISE_NAME = "name";
    public static final String COLUMN_EXERCISE_MUSCLEGROUP = "muscle_group";
    public static final String COLUMN_EXERCISE_SERIES = "series";
    public static final String COLUMN_EXERCISE_REPETITION = "repetition";
    public static final String COLUMN_EXERCISE_RESTTIME = "rest_time";
    
    private static final String TABLE_USER_CREATE =
            "CREATE TABLE " + TABLE_USER + " (" +
            COLUMN_USER_ID + "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USER_NAME + " TEXT, " +
            COLUMN_USER_EMAIL + " TEXT, " +
            COLUMN_USER_PASSWORD + " TEXT, " +
            COLUMN_USER_GENDER + " TEXT, " +
            COLUMN_USER_WIDTH + " REAL, " +
            COLUMN_USER_HEIGHT + " REAL, " +
            COLUMN_USER_SIZE + " REAL);";
    
    private static final String TABLE_CREATE_TRAINING =
            "CREATE TABLE " + TABLE_TRAINING + " (" +
            COLUMN_TRAINING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TRAINING_NAME + " TEXT, " +
            COLUMN_TRAINING_USER_ID + " INTEGER, " +
            COLUMN_TRAINING_DW + " TEXT, " +
            COLUMN_TRAINING_TYPE + " TEXT);";

    private static final String TABLE_CREATE_EXERCISE =
            "CREATE TABLE " + TABLE_EXERCISE + " (" +
            COLUMN_EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_EXERCISE_NAME + " TEXT, " +
            COLUMN_EXERCISE_MUSCLEGROUP + " TEXT, " +
            COLUMN_EXERCISE_SERIES + " INTEGER, " +
            COLUMN_EXERCISE_REPETITION + " INTEGER, " +
            COLUMN_EXERCISE_RESTTIME + " INTEGER);";
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER_CREATE);
        db.execSQL(TABLE_CREATE_TRAINING);
        db.execSQL(TABLE_CREATE_EXERCISE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_CREATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAINING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
        onCreate(db);
    }

}