package com.analyzer.utils;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StatisticalAnalyzerTest {
    
    private StatisticalAnalyzer analyzer;
    
    @Before
    public void setUp() {
        analyzer = new StatisticalAnalyzer();
    }
    
    @Test
    public void testCalculateMean() {
        double[] values = {1.0, 2.0, 3.0, 4.0, 5.0};
        double expected = 3.0;
        double result = analyzer.calculateMean(values);
        assertEquals(expected, result, 0.0001);
    }
    
    @Test
    public void testCalculateStandardDeviation() {
        double[] values = {1.0, 2.0, 3.0, 4.0, 5.0};
        double expected = 1.4142;
        double result = analyzer.calculateStandardDeviation(values);
        assertEquals(expected, result, 0.0001);
    }
}