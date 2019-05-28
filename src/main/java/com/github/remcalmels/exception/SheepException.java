package com.github.remcalmels.exception;

/**
 * Exception thrown on sheep buildling error
 */
public class SheepException extends Exception {

    public SheepException(String message) {
        super(message);
    }

    public SheepException(Throwable cause) {
        super(cause);
    }

    public SheepException(String message, Throwable cause) {
        super(message, cause);
    }
}
