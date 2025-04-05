package com.analyzer.utils;

public class StatisticalAnalyzer {

    public double calculateMean(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.length;
    }

    public double calculateStandardDeviation(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
        double mean = calculateMean(values);
        double sumSquaredDifferences = 0;
        for (double value : values) {
            sumSquaredDifferences += Math.pow(value - mean, 2);
        }
        return Math.sqrt(sumSquaredDifferences / values.length);
    }
}