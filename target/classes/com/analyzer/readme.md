# Manufacturing Process Analyzer

## Overview
The Manufacturing Process Analyzer is a Java desktop application designed to help manufacturing engineers and technicians monitor, analyze, and optimize industrial processes. This tool demonstrates advanced process monitoring capabilities with a focus on statistical analysis of measurement data, making it particularly relevant for high-precision manufacturing environments like semiconductor fabrication.

## Purpose
This application serves as a comprehensive solution for:
- Collecting and storing measurement data from manufacturing processes
- Performing statistical analysis to identify process deviations
- Generating reports on process stability and performance
- Visualizing process data for better decision-making

The system is especially valuable in environments requiring tight process control, such as semiconductor manufacturing, where minute variations can significantly impact product quality and yield.

## Key Features
- Process Management: Add and monitor various manufacturing processes
- Data Collection: Record measurements with timestamps
- Statistical Analysis: Calculate mean, standard deviation, and other statistical metrics
- Report Generation: Create detailed reports on process performance and stability
- User-Friendly Interface: Intuitive dashboard for easy interaction with the system

## Technologies Used
- Java: Core programming language for object-oriented implementation
- Swing: Java GUI toolkit for creating the desktop interface
- JDBC: Java Database Connectivity for database operations
- H2 Database: Lightweight in-memory database for data storage
- JUnit: Framework for unit testing components
- MVC Architecture: Model-View-Controller pattern for clean code organization
- Stream API: Modern Java streams for efficient data processing
- Lambda Expressions: Functional programming features for concise code

## Architecture
The application follows a clean MVC (Model-View-Controller) architecture:
- Models: Represent core business objects (Process, Measurement)
- Views: User interface components for data visualization
- Controllers: Handle business logic and data management
- Services: Provide specialized functionality (analysis, database operations)
- Utils: Statistical analysis utilities

## Application Structure
- Main: Application entry point
- Models: Business domain objects
- Controllers: Process and data controllers
- Services: Analysis and database services
- Views: User interface components
- Utils: Helper utilities for data analysis