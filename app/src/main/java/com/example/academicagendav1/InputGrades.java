package com.example.academicagendav1;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.textclassifier.TextClassification;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.academicagendav1.ui.dashboard.DashboardViewModel;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

class InputGradesActivity extends AppCompatActivity {

    private Bundle savedInstanceState;
    private String input;
    private BreakIterator textViewEnteredGrades;
    private DashboardViewModel subjectEditText;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_grades);

        EditText subjectEditText = findViewById(R.id.subjectEditText);
        EditText gradeEditText = findViewById(R.id.gradeEditText);
        TextView textViewEnteredGrades = findViewById(R.id.textViewEnteredGrades);


        TextClassification.Builder addGradeButton = new TextClassification.Builder();
        addGradeButton.setOnClickListener(v -> {
            // Create an Intent to start InputGradesActivity
            Intent intent = new Intent(this, InputGradesActivity.class);
            startActivity(intent);
        });

    }

    private void addGrade() {
        // Retrieve the subject and grade entered by the user
        String subject = subjectEditText.getText().toString();
        String gradeEditText = "";
        String i = "";
        String grade = String.valueOf(gradeEditText.charAt(Integer.parseInt(i)));

        // Validate input
        if (subject.isEmpty() || grade.isEmpty()) {
            // Show an error message or handle the validation failure
            return;
        }

        // Add the grade using Grade class
        Grade inputGrade = new Grade(subject, grade);


        // Update the TextView to display the entered grade
        String currentText = textViewEnteredGrades.getText().toString();
        String newText = currentText + "\nSubject: " + subject + "\nGrade: " + grade + "\n";
        textViewEnteredGrades.setText(newText);

        // Optional: Clear EditText fields after adding a grade
        try {
            subjectEditText.getText().wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            gradeEditText.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }




    private EditText editTextGrade;

    {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_input_grades);

            editTextGrade = findViewById(R.id.editTextGrade);

            // Set an InputFilter for the EditText to restrict input
            InputFilter decimalFilter = this::filter;
            editTextGrade.setFilters(new InputFilter[]{decimalFilter});
        }

        private CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            String regex = "(\\d{0,2}\\.\\d{0,2})|([A-Za-z]{0,2})";

            CharSequence charSequence = input.matches(regex) ? null : "";
            return charSequence;
        }


        public static class Grade {
        private int credits = 0;
        private final Context context = null;
        private String gradeValue;

        public Grade(String gradeValue, String credits) {

        }

            public Grade(String a, int i) {

            }

            public String getGradeValue() {
            return gradeValue;
        }


        public void setGradeValue(String gradeValue) {
            this.gradeValue = gradeValue;
        }

        public char[] getSubject() {
            return new char[0];
        }

        public int getCredits() {
            return credits;
        }


        public void setCredits(int credits) {
            this.credits = credits;
        }


        public class GPACalculator {

            public double calculateCumulativeGPA(List<Grade> grades) {
                double totalGradePoints = 0.0;
                int totalCredits = 0;

                for (Grade grade : grades) {
                    double gradeValue = convertGradeToNumeric(grade.getGradeValue());
                    int credits = grade.getCredits();

                    totalGradePoints += gradeValue * credits;
                    totalCredits += credits;
                }

                if (totalCredits == 0) {
                    // Avoid division by zero
                    return 0.0;
                }

                return totalGradePoints / totalCredits;
            }

            private double convertGradeToNumeric(String gradeValue) {
                // For simplicity, we are assuming A=4.0, B=3.0, C=2.0, D=1.0, F=0.0
                switch (gradeValue) {
                    case "A":
                        return 4.0;
                    case "B":
                        return 3.0;
                    case "C":
                        return 2.0;
                    case "D":
                        return 1.0;
                    default:
                        return 0.0;
                }
            }
        }

        public static void main(String[] args) {
            // Example usage
            List<Grade> grades = new ArrayList<>();

            // Sample grades with associated credits
            grades.add(new Grade("A", 4));
            grades.add(new Grade("B", 3));
            grades.add(new Grade("C", 3));

            double cumulativeGPA = calculateCumulativeGPA(grades);
            System.out.println("Cumulative GPA: " + cumulativeGPA);
        }
    }

    public  static double calculateCumulativeGPA(List<Grade> grades) {
        double totalGradePoints = 0.0;
        int i = 0;
        int totalCredits = i;
        {

                return i;
            }
        }

        public int getCredits() {


            return 0;
        }}
