package com.borisjerev.sumUp.exception;

public class NoTasksFoundException extends RuntimeException {

    public NoTasksFoundException(String message) {
        super(message);
    }
}
