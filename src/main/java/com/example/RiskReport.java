package com.example;

public class RiskReport {
    private String patientId;  // Associated patient ID
    private String summary;    // Risk assessment summary

    // Constructor
    public RiskReport(String patientId, String summary) {
        this.patientId = patientId;
        this.summary = summary;
    }

    // Getters
    public String getPatientId() {
        return patientId;
    }

    public String getSummary() {
        return summary;
    }

    // Setters (if needed for flexibility)
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    // Format the report for saving to a file
    @Override
    public String toString() {
        return "Patient ID: " + patientId + "\n"
             + "Risk Assessment Summary: " + summary;
    }
}
