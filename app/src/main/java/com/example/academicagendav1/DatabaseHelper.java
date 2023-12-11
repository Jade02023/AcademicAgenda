package com.example.academicagendav1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String COLUMN_USERNAME = "";


    public static final String COLUMN_TASK_DESCRIPTION = "";



    // Database information
    private static final String DATABASE_NAME = "academic_agenda.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_USERS = "users";
    public static final String TABLE_CALENDAR_EVENTS = "calendar_events";
    public static final String TABLE_GRADES = "grades";
    public static final String TABLE_TASKS = "tasks";

    // Common columns
    public static final String COLUMN_ID = "id";

    // Users table columns
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    // Calendar events table columns
    public static final String COLUMN_EVENT_NAME = "event_name";
    public static final String COLUMN_EVENT_DATE = "event_date";
    public static final String COLUMN_EVENT_DESCRIPTION = "event_description";

    // Grades table columns
    public static final String COLUMN_COURSE_NAME = "course_name";
    public static final String COLUMN_GRADE = "grade";

    // Tasks table columns
    public static final String COLUMN_TASK_NAME = "task_name";
    public static final String COLUMN_DUE_DATE = "due_date";

    // Create tables
    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_EMAIL + " TEXT," +
                    COLUMN_PASSWORD + " TEXT)";

    private static final String CREATE_CALENDAR_EVENTS_TABLE =
            "CREATE TABLE " + TABLE_CALENDAR_EVENTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_EVENT_NAME + " TEXT," +
                    COLUMN_EVENT_DATE + " INTEGER," +
                    COLUMN_EVENT_DESCRIPTION + " TEXT)";

    private static final String CREATE_GRADES_TABLE =
            "CREATE TABLE " + TABLE_GRADES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_COURSE_NAME + " TEXT," +
                    COLUMN_GRADE + " REAL)";

    private static final String CREATE_TASKS_TABLE =
            "CREATE TABLE " + TABLE_TASKS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_TASK_NAME + " TEXT," +
                    COLUMN_DUE_DATE + " INTEGER)";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_CALENDAR_EVENTS_TABLE);
        db.execSQL(CREATE_GRADES_TABLE);
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALENDAR_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRADES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);

        // Recreate the tables
        onCreate(db);
    }

}