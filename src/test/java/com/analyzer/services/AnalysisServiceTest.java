package com.analyzer.services;

import org.junit.Before;
import org.junit.Test;
import com.analyzer.models.Measurement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

public class AnalysisServiceTest {

    private AnalysisService analysisService;

    @Before
    public void setUp() {
        analysisService = new AnalysisService();
    }

    @Test
    public void testAnalyzeData() {
        // Sample data for analysis
        double[] data = {1.0, 2.0, 3.0, 4.0, 5.0};
        
        // Convert double[] to List<Measurement>
        List<Measurement> measurements = Arrays.stream(data)
                .mapToObj(value -> new Measurement(0, value, LocalDateTime.now()))
                .collect(Collectors.toList());

        // Perform analysis
        analysisService.analyzeData(measurements);
        // No assert needed since we're just verifying it runs without exceptions
    }

    @Test
    public void testGenerateReport() {
        // Sample data for analysis
        double[] data = {1.0, 2.0, 3.0, 4.0, 5.0};
        
        // Convert double[] to List<Measurement>
        List<Measurement> measurements = Arrays.stream(data)
                .mapToObj(value -> new Measurement(0, value, LocalDateTime.now()))
                .collect(Collectors.toList());

        // Generate report
        analysisService.generateReport(measurements);
        // No assert needed since we're just verifying it runs without exceptions
    }
}