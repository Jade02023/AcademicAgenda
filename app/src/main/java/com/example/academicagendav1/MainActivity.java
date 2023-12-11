package com.example.academicagendav1;

import static android.os.Build.VERSION_CODES.R;
import static android.provider.CalendarContract.CONTENT_URI;
import static java.lang.String.format;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME;
    private static final String GRADES_KEY;

    // Mapping between letter grades and their corresponding GPA values
    private static final String[] LETTER_GRADES = {"A", "B", "C", "D", "F"};
    private static final double[] GPA_VALUES = {4.0, 3.0, 2.0, 1.0, 0.0};

    static {
        PREFS_NAME = "MyPrefs";
        GRADES_KEY = "grades";
    }
    private List<String> tasksList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        findViewById(R.id.startTimeEditText);
        findViewById(R.id.endTimeEditText);
        findViewById(R.id.gradesTextView);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> showTimePickerDialog());

        Button viewCalendarButton = findViewById(R.id.viewCalendarButton);
        Button addEventButton = findViewById(R.id.addEventButton);
        Button viewGradesButton = findViewById(R.id.viewGradesButton);
        Button inputGradesButton = findViewById(R.id.inputGradesButton);
        Button manageTasksButton = findViewById(R.id.manageTasksButton);
        EditText gradesEditText = findViewById(R.id.gradesEditText);
        Button saveGradesButton = findViewById(R.id.saveGradesButton);

        saveGradesButton.setOnClickListener(v -> saveGrades(gradesEditText.getText().toString()));

        viewCalendarButton.setOnClickListener(v -> viewCalendar());
        addEventButton.setOnClickListener(v -> addEvent());
        viewGradesButton.setOnClickListener(v -> viewGrades());
        inputGradesButton.setOnClickListener(v -> inputGrades());
        manageTasksButton.setOnClickListener(v -> manageTasks());

        // Initialize the tasksList
        tasksList = new ArrayList<>();

        // Initialize RecyclerView and set its layout manager
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the TaskAdapter with the tasksList
        taskAdapter = new TaskAdapter(tasksList);

        // Set the adapter for the RecyclerView
        recyclerView.setAdapter(taskAdapter);

        // Example: Adding a task to the list
        addTask("Example Task 1");
    }

    private void manageTasks() {
    }

    // Method to add a task to the list
    private void addTask(String task) {
        tasksList.add(task);
        taskAdapter.notifyDataSetChanged(); // Notify the adapter that the data set has changed
    }
    private class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

        private List<String> tasks;

        public TaskAdapter(List<String> tasks) {
            this.tasks = tasks;
        }

        @NonNull
        @Override
        public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
            return new TaskViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
            String task = tasks.get(position);
            holder.bind(task);
        }

        @Override
        public int getItemCount() {
            return tasks.size();
        }

        // ViewHolder class
        public class TaskViewHolder extends RecyclerView.ViewHolder {

            private TextView taskTextView;

            public TaskViewHolder(@NonNull View itemView) {
                super(itemView);
                taskTextView = itemView.findViewById(R.id.taskTextView);
            }

            public void bind(String task) {
                taskTextView.setText(task);
            }
        }
    }

    private void addEvent() {
        // Set the event details
        String title = "Your Event Title";
        String description = "Event Description";
        long startTimeMillis = System.currentTimeMillis() + 1000 * 60; // Start time in milliseconds (1 minute from now)
        long endTimeMillis = startTimeMillis + 1000 * 60 * 60; // End time in milliseconds (1 hour from start time)

        // Create a new calendar event
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, startTimeMillis);
        values.put(CalendarContract.Events.DTEND, endTimeMillis);
        values.put(CalendarContract.Events.TITLE, title);
        values.put(CalendarContract.Events.DESCRIPTION, description);
        values.put(CalendarContract.Events.CALENDAR_ID, 1); // Use the primary calendar

        // Insert the event into the calendar
        Uri insert = getContentResolver().insert(CalendarContract.Events.CONTENT_URI, values);

        // Display text indicating that the event has been added
        Toast.makeText(this, "Event added to calendar", Toast.LENGTH_SHORT).show();
    }

    int findIndex(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(value)) {
                return i;
            }
        }
        return -1; // Not found
    }

    private void calculateAndDisplayGPA(String grades) {
        if (grades == null || grades.trim().isEmpty()) {
            // Handle empty or null grades input
            Toast.makeText(this, "Please enter valid grades", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tokenize the entered grades
        String[] individualGrades = grades.split("\\s+");

        if (individualGrades.length == 0) {
            // Handle case where no valid grades are found
            Toast.makeText(this, "No valid grades found", Toast.LENGTH_SHORT).show();
            return;
        }

        double totalGPA = 0.0;
        int validGradesCount = 0;

        // Loop through individual grades and calculate GPA
        for (String grade : individualGrades) {
            // Find the corresponding GPA value for the letter grade
            int index = findIndex(LETTER_GRADES, grade);
            if (index != -1) {
                totalGPA += GPA_VALUES[index];
                validGradesCount++;
            }
        }

        if (validGradesCount == 0) {
            // Handle case where no valid grades are found
            Toast.makeText(this, "No valid grades found", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate the average GPA
        double averageGPA = totalGPA / validGradesCount;

        // Display the GPA
        Toast.makeText(this, "Your GPA is: " + averageGPA, Toast.LENGTH_SHORT).show();
    }

    private void saveGrades(String grades) {
        // Get SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Get existing saved grades
        String existingGrades = sharedPreferences.getString(GRADES_KEY, "");

        // Concatenate new grades with existing grades
        String allGrades = existingGrades + "\n" + grades;

        // Save the combined grades
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(GRADES_KEY, allGrades);
        editor.apply();

        Toast.makeText(this, "Grades saved!", Toast.LENGTH_SHORT).show();
    }

    private void inputGrades() {
        // Create an AlertDialog to input grades
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Input Grades");

        // Create an EditText for the user to input grades
        final EditText gradeInput = new EditText(this);
        gradeInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        gradeInput.setHint("Enter grade");

        // Create a LinearLayout to add the EditText to
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(gradeInput);

        // Set the custom layout to the dialog
        builder.setView(layout);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle the inputted grade
                String inputtedGrade = gradeInput.getText().toString();
                if (!inputtedGrade.isEmpty()) {
                    // Process the grade, save it, or perform any desired action
                    // For now, let's just display a toast with the inputted grade
                    Toast.makeText(MainActivity.this, "Inputted Grade: " + inputtedGrade, Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User cancelled the input
                dialog.cancel();
            }
        });

        // Show the dialog
        builder.show();
    }

    private List<String> getGradesData() {
        //list grades
        List<String> grades = new ArrayList<>();
        grades.add("Subject A: A+");
        grades.add("Subject B: B");
        grades.add("Subject C: A-");


        return grades;
    }

    private void viewGrades() {
        //display grades
        TextView gradesTextView = findViewById(R.id.gradesTextView);

        // gather grades
        List<String> grades = getGradesData();

        // Display the grades in the TextView
        StringBuilder gradesText = new StringBuilder("Grades:\n");
        for (String grade : grades) {
            gradesText.append(grade).append("\n");
        }
        gradesTextView.setText(gradesText.toString());
    }

    private void viewCalendar() {
        // Create an intent to view the calendar
        Intent calendarIntent = new Intent(Intent.ACTION_VIEW);
        calendarIntent.setData(CONTENT_URI);
        startActivity(calendarIntent);
    }

    private void handleSelectedTime(int hour, int minute) {
        // Do something with the selected time
        String selectedTime = format("%02d:%02d", hour, minute);
        // show time
        ((TextView) findViewById(R.id.selectedTimeTextView)).setText(selectedTime);
    }

    private void showTimePickerDialog() {
        // Get the current time
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a time picker dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Handle the selected time
                        handleSelectedTime(hourOfDay, minute);
                    }
                },
                hour,
                minute,
                false // 24-hour format
        );

        // Show the time picker dialog
        timePickerDialog.show();
    }
}





















