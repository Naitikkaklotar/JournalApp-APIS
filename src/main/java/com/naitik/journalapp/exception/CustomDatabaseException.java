package com.naitik.journalapp.exception;


public class CustomDatabaseException extends RuntimeException {
    public CustomDatabaseException(String message) {
        super(message);
    }
    public CustomDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
