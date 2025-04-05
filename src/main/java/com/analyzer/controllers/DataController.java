package com.analyzer.controllers;

import com.analyzer.models.Measurement;
import com.analyzer.services.DatabaseService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataController {
    private DatabaseService databaseService;
    private List<Measurement> measurements;
    
    public DataController() {
        this.databaseService = new DatabaseService();
        this.measurements = new ArrayList<>();
    }
    
    public boolean saveMeasurement(Measurement measurement) {
        try {
            databaseService.executeUpdate(
                "INSERT INTO measurements (value, timestamp) VALUES (?, ?)",
                measurement.getValue(),
                measurement.getTimestamp());
            measurements.add(measurement);
            return true;
        } catch (Exception e) {
            System.err.println("Error saving measurement: " + e.getMessage());
            return false;
        }
    }
    
    public List<Measurement> getAllMeasurements() {
        return new ArrayList<>(measurements);
    }
    
    public void initialize() {
        System.out.println("Initializing Data Controller...");
        // Initialize database connection and tables
        databaseService.initializeDatabase();
        
        // Add some sample data
        saveMeasurement(new Measurement(1, 5.2, LocalDateTime.now()));
        saveMeasurement(new Measurement(2, 5.7, LocalDateTime.now()));
        saveMeasurement(new Measurement(3, 5.1, LocalDateTime.now()));
    }
}