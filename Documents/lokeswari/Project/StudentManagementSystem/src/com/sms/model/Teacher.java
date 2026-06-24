package com.sms.model;

/**
 * Teacher class extending Person.
 * Demonstrates OOP: Inheritance & Polymorphism
 */
public class Teacher extends Person {

    private String department;
    private String subject;

    public Teacher(String id, String name, String email, int age,
                   String department, String subject) {
        super(id, name, email, age);
        this.department = department;
        this.subject    = subject;
    }

    @Override
    public String getRole() {
        return "TEACHER";
    }

    @Override
    public String toCSV() {
        return String.join(",", id, name, email,
                String.valueOf(age), department, subject);
    }

    public static Teacher fromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Teacher(
                parts[0], parts[1], parts[2],
                Integer.parseInt(parts[3]),
                parts[4], parts[5]
        );
    }

    public String getDepartment()           { return department; }
    public String getSubject()              { return subject; }
    public void setDepartment(String dept)  { this.department = dept; }
    public void setSubject(String subject)  { this.subject = subject; }

    @Override
    public String toString() {
        return super.toString()
                + String.format(" | Dept: %-15s | Subject: %s",
                department, subject);
    }
}
