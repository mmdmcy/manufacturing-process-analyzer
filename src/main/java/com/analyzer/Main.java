package com.analyzer;

import com.analyzer.views.DashboardView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        System.out.println("Manufacturing Process Analyzer is starting...");
        
        // Launch the UI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            DashboardView dashboard = new DashboardView();
            dashboard.displayDashboard();
        });

        System.out.println("UI initialization complete.");
    }
}