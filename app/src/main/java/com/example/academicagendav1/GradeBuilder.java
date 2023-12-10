package com.example.academicagendav1;

public class GradeBuilder {
    private String gradeValue;
    private int credits;

    public GradeBuilder setGradeValue(String gradeValue) {
        this.gradeValue = gradeValue;
        return this;
    }

    public GradeBuilder setCredits(int credits) {
        this.credits = credits;
        return this;
    }

    public InputGradesActivity.Grade createGrade() {
        return new InputGradesActivity.Grade(gradeValue, credits);
    }
}