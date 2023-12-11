package com.example.academicagendav1;

class GradeManager {

    private static String grade = "";


    // Constructor
    public GradeManager(String grade) {
        GradeManager.grade = grade;
        // Initialize or fetch grades from data source

    }

    // Getter for all grades
    public void getAllGrades() {
    }

    // Example usage
    public static void main(String[] args) {
        GradeManager gradeManager = new GradeManager(grade);
        gradeManager.getAllGrades();

        // Print all grades


    }}


