package com.analyzer.services;

import java.util.List;
import com.analyzer.models.Measurement;
import com.analyzer.utils.StatisticalAnalyzer;

public class AnalysisService {

    private StatisticalAnalyzer statisticalAnalyzer;

    public AnalysisService() {
        this.statisticalAnalyzer = new StatisticalAnalyzer();
    }

    public double calculateMean(List<Measurement> measurements) {
        if (measurements == null || measurements.isEmpty()) {
            throw new IllegalArgumentException("Measurement data cannot be null or empty");
        }
        
        double[] values = measurements.stream()
                                    .mapToDouble(Measurement::getValue)
                                    .toArray();
        
        return statisticalAnalyzer.calculateMean(values);
    }
    
    public double calculateStandardDeviation(List<Measurement> measurements) {
        if (measurements == null || measurements.isEmpty()) {
            throw new IllegalArgumentException("Measurement data cannot be null or empty");
        }
        
        double[] values = measurements.stream()
                                    .mapToDouble(Measurement::getValue)
                                    .toArray();
        
        return statisticalAnalyzer.calculateStandardDeviation(values);
    }

    public void analyzeData(List<Measurement> measurements) {
        if (measurements == null || measurements.isEmpty()) {
            throw new IllegalArgumentException("Measurement data cannot be null or empty");
        }

        double mean = calculateMean(measurements);
        double standardDeviation = calculateStandardDeviation(measurements);

        // Further analysis logic can be added here
        System.out.println("Mean: " + mean);
        System.out.println("Standard Deviation: " + standardDeviation);
    }

    public void generateReport(List<Measurement> measurements) {
        // Logic to generate a report based on the analysis
        analyzeData(measurements);
        // Report generation logic can be added here
    }
}