package com.project.app.exption.types;

public class ErrorCustonController extends RuntimeException {

    public ErrorCustonController(String message) {
        super(message);
    }
}
