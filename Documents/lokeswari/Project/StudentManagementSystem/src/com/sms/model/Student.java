package com.sms.model;

/**
 * Student class extending Person.
 * Demonstrates OOP: Inheritance & Polymorphism
 */
public class Student extends Person {

    private String course;
    private double gpa;
    private int yearLevel;

    public Student(String id, String name, String email, int age,
                   String course, double gpa, int yearLevel) {
        super(id, name, email, age);
        this.course  = course;
        this.gpa     = gpa;
        this.yearLevel = yearLevel;
    }

    @Override
    public String getRole() {
        return "STUDENT";
    }

    /** Serialize to CSV for file storage */
    @Override
    public String toCSV() {
        return String.join(",", id, name, email,
                String.valueOf(age), course,
                String.valueOf(gpa), String.valueOf(yearLevel));
    }

    /** Deserialize from CSV */
    public static Student fromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Student(
                parts[0], parts[1], parts[2],
                Integer.parseInt(parts[3]),
                parts[4],
                Double.parseDouble(parts[5]),
                Integer.parseInt(parts[6])
        );
    }

    // Getters and Setters
    public String getCourse()           { return course; }
    public double getGpa()              { return gpa; }
    public int    getYearLevel()        { return yearLevel; }

    public void setCourse(String course)     { this.course = course; }
    public void setGpa(double gpa)           { this.gpa = gpa; }
    public void setYearLevel(int yearLevel)  { this.yearLevel = yearLevel; }

    @Override
    public String toString() {
        return super.toString()
                + String.format(" | Course: %-15s | Year: %d | GPA: %.2f",
                course, yearLevel, gpa);
    }
}
