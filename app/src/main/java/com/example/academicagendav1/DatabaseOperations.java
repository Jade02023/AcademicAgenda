package com.example.academicagendav1;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.widget.Toast.makeText;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DatabaseOperations {
    private static final String TAG = "DatabaseOperations";

    private final DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private Context context;
    private String eventName;
    private long eventDate;
    private String searchTerm;


    // Constructor
    public DatabaseOperations(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public DatabaseOperations() {

        dbHelper = null;
    }

    // Open the database for writing
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Close the database
    public void close() {
        dbHelper.close();
    }

    // User Registration
    public long registerUser(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);

        return database.insert(DatabaseHelper.TABLE_USERS, null, values);
    }

    // User Login
    public boolean loginUser(String username, String password) {
        String selection = DatabaseHelper.COLUMN_USERNAME + "=? AND " + DatabaseHelper.COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {username, password};

        String[] columns = {DatabaseHelper.COLUMN_USERNAME};
        Cursor cursor = database.query(DatabaseHelper.TABLE_USERS, columns, selection, selectionArgs, null, null, null);

        boolean loginSuccessful = cursor != null && cursor.moveToFirst();
        if (cursor != null) {
            cursor.close();
        }

        return loginSuccessful;
    }

    // View Academic Calendar
    public List<String> viewAcademicCalendar() {
        List<String> academicCalendar = new ArrayList<>();
        String[] columns = {DatabaseHelper.COLUMN_EVENT_NAME, DatabaseHelper.COLUMN_EVENT_DATE};
        Cursor cursor = database.query(DatabaseHelper.TABLE_CALENDAR_EVENTS, columns, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String eventName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_NAME));
                long eventDate = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_DATE));

                academicCalendar.add(eventName + " - " + eventDate);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return academicCalendar;
    }

    // Add Event to Calendar
    public long addEventToCalendar(String eventName, long eventDate, String eventDescription) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EVENT_NAME, eventName);
        values.put(DatabaseHelper.COLUMN_EVENT_DATE, eventDate);
        values.put(DatabaseHelper.COLUMN_EVENT_DESCRIPTION, eventDescription);

        return database.insert(DatabaseHelper.TABLE_CALENDAR_EVENTS, null, values);
    }

    // View Grades
    public List<String> viewGrades() {
        List<String> gradesList = new ArrayList<>();
        String[] columns = {DatabaseHelper.COLUMN_COURSE_NAME, DatabaseHelper.COLUMN_GRADE};
        Cursor cursor = database.query(DatabaseHelper.TABLE_GRADES, columns, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String courseName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COURSE_NAME));
                double grade = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_GRADE));

                gradesList.add(courseName + " - " + grade);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return gradesList;
    }

    // Input Grades
    public void inputGrade(String courseName, double grade) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_COURSE_NAME, courseName);
        values.put(DatabaseHelper.COLUMN_GRADE, grade);
        database.insert(DatabaseHelper.TABLE_GRADES, null, values);
    }

    // Manage Tasks
    public long addTask(String taskName, String taskDescription) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TASK_NAME, taskName);
        values.put(String.valueOf(DatabaseHelper.COLUMN_TASK_DESCRIPTION),
                taskDescription);

        return database.insert(DatabaseHelper.TABLE_TASKS, null, values);
    }

    // Task Notifications
    // Add logic for task notifications here

    // Search for Events/Tasks
    public List<String> searchEventsAndTasks(String searchTerm) {
        List<String> searchResults = new ArrayList<>();

        /* Search in Calendar Events */
        boolean b;
        if (!searchResults.addAll(searchInEvents(searchTerm))) {
            b = false;
        } else {
            b = true;
        }


        // Search in Tasks
        searchResults.addAll(searchInTasks(searchTerm));

        return searchResults;
    }


        // Search for events
        private List<String> searchInEvents (String searchTerm){
            List<String> searchResults = new ArrayList<>();
            String[] columns = {DatabaseHelper.COLUMN_EVENT_NAME, DatabaseHelper.COLUMN_EVENT_DATE};
            String selection = DatabaseHelper.COLUMN_EVENT_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + searchTerm + "%"};

            Cursor cursor = database.query(DatabaseHelper.TABLE_CALENDAR_EVENTS, columns, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String eventName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_NAME));
                    long eventDate = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_DATE));

                    searchResults.add("Event: " + eventName + " - Date: " + eventDate);
                } while (cursor.moveToNext());

                cursor.close();
            }

            return searchResults;
        }

        // Search for tasks
        private List<String> searchInTasks (String searchTerm){
            List<String> searchResults = new ArrayList<>();
            String[] columns = {DatabaseHelper.COLUMN_TASK_NAME, String.valueOf(DatabaseHelper.COLUMN_TASK_DESCRIPTION)};
            String selection = DatabaseHelper.COLUMN_TASK_NAME + " LIKE ? OR " + DatabaseHelper.COLUMN_TASK_DESCRIPTION + " LIKE ?";
            String[] selectionArgs = {"%" + searchTerm + "%", "%" + searchTerm + "%"};

            Cursor cursor = database.query(DatabaseHelper.TABLE_TASKS, columns, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String taskName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TASK_NAME));
                    String taskDescription;
                    taskDescription = cursor.getString(cursor.getColumnIndex(String.valueOf(DatabaseHelper.COLUMN_TASK_DESCRIPTION)));

                    searchResults.add("Task: " + taskName + " - Description: " + taskDescription);
                } while (cursor.moveToNext());

                cursor.close();
            }

            return searchResults;
        }


        public List<Grade> getAllGrades () {

            return null;
        }

        public void deleteEventFromCalendar (String eventName,long eventDate){
        }


        public class CalendarEvent {
        }

        public static class Grade {

            private String grade;

            // Constructor
            public Grade(String grade) {
                this.grade = grade;
            }

            // Getter for grade
            public String getGrade() {
                return grade;
            }

            // Setter for grade
            public void setGrade(String grade) {
                this.grade = grade;
            }
        }

    }
    

