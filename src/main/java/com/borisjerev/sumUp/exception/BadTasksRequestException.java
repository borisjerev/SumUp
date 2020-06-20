package com.borisjerev.sumUp.exception;

public class BadTasksRequestException extends RuntimeException {

    public BadTasksRequestException(String message) {
        super(message);
    }
}
