package com.analyzer.controllers;

import com.analyzer.models.Process;
import com.analyzer.services.AnalysisService;
import java.util.ArrayList;
import java.util.List;

public class ProcessController {
    private AnalysisService analysisService;
    private List<Process> processes;
    
    public ProcessController() {
        this.analysisService = new AnalysisService();
        this.processes = new ArrayList<>();
    }
    
    public void startProcess(Process process) {
        // Logic to start the manufacturing process
        System.out.println("Starting process: " + process.getName());
        // Additional implementation here
    }

    public void stopProcess(Process process) {
        // Logic to stop the manufacturing process
        System.out.println("Stopping process: " + process.getName());
        // Additional implementation here
    }
    
    public void monitorProcess(Process process) {
        System.out.println("Monitoring process: " + process.getName());
        // Use analysisService to analyze process data
        // analysisService.analyzeData(...);
    }
    
    public boolean addProcess(Process process) {
        return processes.add(process);
    }
    
    public List<Process> getAllProcesses() {
        return new ArrayList<>(processes);
    }
    
    public void initialize() {
        System.out.println("Initializing Process Controller...");
        // Add some sample processes
        addProcess(new Process(1, "Wafer Etching", "Silicon wafer etching process", true));
        addProcess(new Process(2, "Lithography", "Photolithography process", true));
        addProcess(new Process(3, "Inspection", "Automated optical inspection", false));
    }
}