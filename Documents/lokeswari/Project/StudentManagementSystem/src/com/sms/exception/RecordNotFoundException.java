package com.sms.exception;

/**
 * Thrown when a student/teacher with the given ID is not found.
 */
public class RecordNotFoundException extends Exception {
    public RecordNotFoundException(String message) {
        super(message);
    }
}
