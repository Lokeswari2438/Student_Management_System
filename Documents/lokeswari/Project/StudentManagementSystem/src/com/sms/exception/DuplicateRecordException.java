package com.sms.exception;

/**
 * Thrown when trying to add a record with a duplicate ID.
 */
public class DuplicateRecordException extends Exception {
    public DuplicateRecordException(String message) {
        super(message);
    }
}
