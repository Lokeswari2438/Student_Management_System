package com.sms.util;

import com.sms.exception.InvalidInputException;

/**
 * Input validation utilities.
 */
public class Validator {

    public static void validateName(String name) throws InvalidInputException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty.");
        }
    }

    public static void validateEmail(String email) throws InvalidInputException {
        if (email == null || !email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidInputException("Invalid email format: " + email);
        }
    }

    public static void validateAge(int age) throws InvalidInputException {
        if (age < 1 || age > 120) {
            throw new InvalidInputException("Age must be between 1 and 120.");
        }
    }

    public static void validateGpa(double gpa) throws InvalidInputException {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new InvalidInputException("GPA must be between 0.0 and 4.0.");
        }
    }

    public static void validateYearLevel(int year) throws InvalidInputException {
        if (year < 1 || year > 6) {
            throw new InvalidInputException("Year level must be between 1 and 6.");
        }
    }
}
