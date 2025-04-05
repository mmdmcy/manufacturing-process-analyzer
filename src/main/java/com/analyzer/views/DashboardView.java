package com.analyzer.views;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.analyzer.controllers.DataController;
import com.analyzer.controllers.ProcessController;
import com.analyzer.models.Measurement;
import com.analyzer.models.Process;
import com.analyzer.services.AnalysisService;

public class DashboardView {
    private JFrame frame;
    private DataController dataController;
    private ProcessController processController;
    private AnalysisService analysisService;
    
    private JList<String> processList;
    private JTable measurementsTable;
    private JLabel analysisResultsLabel;
    private JTextArea reportTextArea;
    
    private DefaultListModel<String> processListModel;
    private Object[][] measurementData;
    
    public DashboardView() {
        frame = new JFrame("Manufacturing Process Analyzer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        // Initialize controllers and services
        dataController = new DataController();
        processController = new ProcessController();
        analysisService = new AnalysisService();
        
        // Initialize data
        dataController.initialize();
        processController.initialize();
        
        // Initialize list model for processes
        processListModel = new DefaultListModel<>();
        updateProcessList();
        
        // Initialize measurement data
        updateMeasurementData();
    }
    
    private void updateProcessList() {
        processListModel.clear();
        for (Process process : processController.getAllProcesses()) {
            processListModel.addElement(process.getName());
        }
    }
    
    private void updateMeasurementData() {
        java.util.List<Measurement> measurements = dataController.getAllMeasurements();
        measurementData = new Object[measurements.size()][3];
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        for (int i = 0; i < measurements.size(); i++) {
            Measurement m = measurements.get(i);
            measurementData[i][0] = m.getId();
            measurementData[i][1] = m.getValue();
            measurementData[i][2] = m.getTimestamp().format(formatter);
        }
    }
    
    public void displayDashboard() {
        // Set up layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create header
        JPanel headerPanel = new JPanel();
        JLabel titleLabel = new JLabel("Manufacturing Process Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        
        // Create content panel
        JPanel contentPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add widgets
        contentPanel.add(createProcessPanel());
        contentPanel.add(createMeasurementsPanel());
        contentPanel.add(createAnalysisPanel());
        contentPanel.add(createReportPanel());
        
        // Assemble the UI
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
    
    private JPanel createProcessPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Processes"));
        
        processList = new JList<>(processListModel);
        
        JButton addButton = new JButton("Add Process");
        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(frame, "Enter process name:");
            if (name != null && !name.trim().isEmpty()) {
                String description = JOptionPane.showInputDialog(frame, "Enter process description:");
                Process newProcess = new Process(processListModel.size() + 1, name, description, true);
                processController.addProcess(newProcess);
                updateProcessList();
            }
        });
        
        JButton monitorButton = new JButton("Monitor Process");
        monitorButton.addActionListener(e -> {
            int selectedIndex = processList.getSelectedIndex();
            if (selectedIndex >= 0) {
                String processName = processListModel.get(selectedIndex);
                JOptionPane.showMessageDialog(frame, "Now monitoring: " + processName);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a process to monitor", 
                        "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(monitorButton);
        
        panel.add(new JScrollPane(processList), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createMeasurementsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Measurements"));
        
        measurementsTable = new JTable(measurementData, new String[]{"ID", "Value", "Timestamp"});
        
        JButton addButton = new JButton("Add Measurement");
        addButton.addActionListener(e -> {
            try {
                String valueStr = JOptionPane.showInputDialog(frame, "Enter measurement value:");
                if (valueStr != null && !valueStr.trim().isEmpty()) {
                    double value = Double.parseDouble(valueStr);
                    Measurement newMeasurement = new Measurement(
                            dataController.getAllMeasurements().size() + 1, 
                            value, 
                            LocalDateTime.now());
                    dataController.saveMeasurement(newMeasurement);
                    updateMeasurementData();
                    measurementsTable.setModel(new javax.swing.table.DefaultTableModel(
                            measurementData, new String[]{"ID", "Value", "Timestamp"}));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number", 
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JButton viewButton = new JButton("View Data");
        viewButton.addActionListener(e -> {
            updateMeasurementData();
            measurementsTable.setModel(new javax.swing.table.DefaultTableModel(
                    measurementData, new String[]{"ID", "Value", "Timestamp"}));
            JOptionPane.showMessageDialog(frame, "Data refreshed");
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        
        panel.add(new JScrollPane(measurementsTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createAnalysisPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Analysis"));
        
        analysisResultsLabel = new JLabel("<html>Mean: -<br>Std Dev: -</html>");
        
        JButton analyzeButton = new JButton("Analyze Data");
        analyzeButton.addActionListener(e -> {
            java.util.List<Measurement> measurements = dataController.getAllMeasurements();
            if (measurements.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No measurements to analyze", 
                        "Empty Dataset", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            double mean = analysisService.calculateMean(measurements);
            double stdDev = analysisService.calculateStandardDeviation(measurements);
            
            analysisResultsLabel.setText(String.format("<html>Mean: %.2f<br>Std Dev: %.2f</html>", mean, stdDev));
        });
        
        JButton visualizeButton = new JButton("Visualize");
        visualizeButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, 
                    "Visualization would be displayed here.\n" +
                    "In a real application, this would show charts and graphs.", 
                    "Data Visualization", JOptionPane.INFORMATION_MESSAGE);
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(analyzeButton);
        buttonPanel.add(visualizeButton);
        
        panel.add(analysisResultsLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createReportPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Reports"));
        
        reportTextArea = new JTextArea("Process performance report will appear here...");
        reportTextArea.setEditable(false);
        
        JButton generateButton = new JButton("Generate Report");
        generateButton.addActionListener(e -> {
            java.util.List<Measurement> measurements = dataController.getAllMeasurements();
            if (measurements.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No measurements for reporting", 
                        "Empty Dataset", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            double mean = analysisService.calculateMean(measurements);
            double stdDev = analysisService.calculateStandardDeviation(measurements);
            
            StringBuilder report = new StringBuilder();
            report.append("=== Manufacturing Process Analysis Report ===\n\n");
            report.append("Date: ").append(LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n\n");
            report.append("Number of measurements: ").append(measurements.size()).append("\n");
            report.append("Mean value: ").append(String.format("%.2f", mean)).append("\n");
            report.append("Standard deviation: ").append(String.format("%.2f", stdDev)).append("\n\n");
            
            // Process stability assessment
            if (stdDev / mean < 0.1) {
                report.append("Process Stability: GOOD - The process is stable and within expected parameters.\n");
            } else if (stdDev / mean < 0.2) {
                report.append("Process Stability: FAIR - The process shows some variation, monitoring recommended.\n");
            } else {
                report.append("Process Stability: POOR - The process has high variation, intervention needed.\n");
            }
            
            reportTextArea.setText(report.toString());
        });
        
        JButton exportButton = new JButton("Export");
        exportButton.addActionListener(e -> {
            if (reportTextArea.getText().startsWith("Process performance report will appear here")) {
                JOptionPane.showMessageDialog(frame, "Please generate a report first", 
                        "No Report", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            JOptionPane.showMessageDialog(frame, 
                    "Report would be exported here.\n" +
                    "In a real application, this would save to a file or send via email.", 
                    "Export Report", JOptionPane.INFORMATION_MESSAGE);
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);
        buttonPanel.add(exportButton);
        
        panel.add(new JScrollPane(reportTextArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
}