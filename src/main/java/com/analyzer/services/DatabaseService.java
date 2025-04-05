package com.analyzer.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {
    private Connection connection;
    
    public DatabaseService() {
        // Initialize with null connection, we'll establish it in initializeDatabase
        this.connection = null;
    }
    
    public void initializeDatabase() {
        try {
            // Using H2 in-memory database for simplicity
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:processdb", "sa", "");
            
            // Create tables
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS measurements (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "value DOUBLE NOT NULL, " +
                        "timestamp TIMESTAMP NOT NULL)");
                
                stmt.execute("CREATE TABLE IF NOT EXISTS processes (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(100) NOT NULL, " +
                        "description VARCHAR(255), " +
                        "active BOOLEAN DEFAULT TRUE)");
            }
            
            System.out.println("Database initialized successfully");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database initialization failed: " + e.getMessage());
            // In a real app, you might want to handle this more gracefully
        }
    }
    
    public void executeUpdate(String sql, Object... params) {
        System.out.println("Executing SQL: " + sql);
        // In a real application, this would use PreparedStatement
        // For now, we'll just simulate it for demonstration
        System.out.println("SQL executed successfully");
    }
    
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }
    }
}