package com.analyzer.services;

import java.util.List;

public class ReportingService {

    public String createReport(List<String> analysisResults) {
        StringBuilder report = new StringBuilder();
        report.append("Manufacturing Process Analysis Report\n");
        report.append("=====================================\n");
        
        for (String result : analysisResults) {
            report.append(result).append("\n");
        }
        
        return report.toString();
    }

    public void exportReport(String reportContent, String filePath) {
        try (java.io.FileWriter fileWriter = new java.io.FileWriter(filePath)) {
            fileWriter.write(reportContent);
            fileWriter.flush();
        } catch (java.io.IOException e) {
            System.err.println("Error exporting report: " + e.getMessage());
        }
    }
}