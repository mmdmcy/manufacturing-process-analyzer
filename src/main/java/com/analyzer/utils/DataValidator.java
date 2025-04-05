package com.analyzer.utils;

public class DataValidator {

    public boolean validateData(String data) {
        // Implement validation logic here
        return data != null && !data.trim().isEmpty();
    }

    public boolean isValid(String data) {
        // Implement additional validation checks if necessary
        return validateData(data);
    }
}