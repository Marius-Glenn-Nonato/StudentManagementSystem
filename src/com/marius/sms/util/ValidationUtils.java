package com.marius.sms.util;

import com.marius.sms.backend.exception.InvalidDataException;

public class ValidationUtils {

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static void validateEmail(String email) {
        if (!isValidEmail(email)) {
            throw new InvalidDataException("Invalid email format: " + email);
        }
    }

    public static void validateNonEmpty(String field, String fieldName) {
        if (field == null || field.trim().isEmpty()) {
            throw new InvalidDataException(fieldName + " cannot be empty.");
        }
    }

    public static void validatePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new InvalidDataException(fieldName + " must be positive.");
        }
    }
}
