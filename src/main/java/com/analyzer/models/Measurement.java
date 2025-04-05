package com.analyzer.models;

import java.time.LocalDateTime;

public class Measurement {
    private int id;
    private double value;
    private LocalDateTime timestamp;

    public Measurement(int id, double value, LocalDateTime timestamp) {
        this.id = id;
        this.value = value;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}