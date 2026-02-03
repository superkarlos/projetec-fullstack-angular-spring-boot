package com.project.app.exption.types;

public class ValidationException  extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}