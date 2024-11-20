package com.example;

public class Doctor extends User {
    // Constructor
    public Doctor(String id, String name, String password) {
        super(id, name, "Doctor", password); // Call the parent User class constructor
    }

    // Analyze scan results and generate a risk report
    public RiskReport analyzeScanResults(ScanResult scanResult) {
        String patientId = scanResult.getPatientId();
        int totalCACScore = scanResult.getTotalCACScore();

        // Determine risk level based on total CAC score
        String riskLevel;
        if (totalCACScore < 100) {
            riskLevel = "Low risk of heart disease.";
        } else if (totalCACScore <= 400) {
            riskLevel = "Moderate risk of heart disease.";
        } else {
            riskLevel = "High risk of heart disease.";
        }

        // Create a new risk report
        RiskReport riskReport = new RiskReport(patientId, riskLevel);
        System.out.println("Risk report generated for Patient ID: " + patientId);
        return riskReport;
    }

    // Send the risk report to the patient
    public void sendRiskReport(RiskReport riskReport, FileHandler fileHandler) {
        String riskReportFile = riskReport.getPatientId() + "_RiskReport.txt";

        // Format the report data
        String reportData = "Patient ID: " + riskReport.getPatientId() + "\n"
                          + "Risk Assessment Summary: " + riskReport.getSummary();

        // Save the risk report to a file
        if (fileHandler.createFile(riskReportFile, reportData)) {
            System.out.println("Risk report sent and saved successfully for Patient ID: " + riskReport.getPatientId());
        } else {
            System.out.println("Error: Unable to save the risk report for Patient ID: " + riskReport.getPatientId());
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Role: Doctor";
    }
}
