package com.project.app.exption;

public record FieldErrorResponse(
        String field,
        String message
) {}
