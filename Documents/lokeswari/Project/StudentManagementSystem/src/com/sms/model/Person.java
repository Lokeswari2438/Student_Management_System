package com.sms.model;

/**
 * Abstract base class representing a Person.
 * Demonstrates OOP: Abstraction & Inheritance
 */
public abstract class Person {

    protected String id;
    protected String name;
    protected String email;
    protected int age;

    public Person(String id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // Abstract method - must be implemented by subclasses (Polymorphism)
    public abstract String getRole();

    public abstract String toCSV();

    // Getters and Setters
    public String getId()             { return id; }
    public String getName()           { return name; }
    public String getEmail()          { return email; }
    public int    getAge()            { return age; }

    public void setName(String name)   { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age)        { this.age = age; }

    @Override
    public String toString() {
        return String.format("[%s] ID: %s | Name: %-20s | Age: %d | Email: %s",
                getRole(), id, name, age, email);
    }
}
