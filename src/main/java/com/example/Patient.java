package com.example;

public class Patient extends User {
    private String patientId; // Unique 5-digit ID for the patient
    private String healthHistory; // Medical history of the patient
    private String insuranceId;   // Insurance ID for the patient

    // Constructor
    public Patient(String id, String name, String password, String patientId, String healthHistory, String insuranceId) {
        super(id, name, "Patient", password); // Call the parent User class constructor
        this.patientId = patientId;
        this.healthHistory = healthHistory;
        this.insuranceId = insuranceId;
    }

    // Getters and Setters
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getHealthHistory() {
        return healthHistory;
    }

    public void setHealthHistory(String healthHistory) {
        this.healthHistory = healthHistory;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    // View scan results by accessing relevant files
    public void viewScanResults(FileHandler fileHandler) {
        String patientInfoFile = patientId + "_PatientInfo.txt";
        String scanResultsFile = patientId + "_CTResults.txt";
        String riskReportFile = patientId + "_RiskReport.txt";

        // Check if patient info file exists
        if (!fileHandler.fileExists(patientInfoFile)) {
            System.out.println("Error: Patient information file not found!");
            return;
        }

        // Read and display patient information
        System.out.println("---- Patient Information ----");
        String patientInfo = fileHandler.readFile(patientInfoFile);
        System.out.println(patientInfo);

        // Check if scan results file exists
        if (!fileHandler.fileExists(scanResultsFile)) {
            System.out.println("Error: No CT scan results available for this patient.");
            return;
        }

        // Read and display scan results
        System.out.println("---- CT Scan Results ----");
        String scanResults = fileHandler.readFile(scanResultsFile);
        System.out.println(scanResults);

        // Check if risk report file exists
        if (!fileHandler.fileExists(riskReportFile)) {
            System.out.println("Error: No risk report available for this patient.");
            return;
        }

        // Read and display the risk report
        System.out.println("---- Risk Report ----");
        String riskReport = fileHandler.readFile(riskReportFile);
        System.out.println(riskReport);
    }

    @Override
    public String toString() {
        return super.toString() + ", Patient ID: " + patientId + ", Health History: " + healthHistory + ", Insurance ID: " + insuranceId;
    }
}
