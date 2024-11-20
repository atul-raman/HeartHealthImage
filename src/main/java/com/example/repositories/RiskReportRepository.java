package com.example.repositories;

import com.example.FileHandler;
import com.example.RiskReport;

public class RiskReportRepository {
    private final FileHandler fileHandler;

    // Constructor
    public RiskReportRepository(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    // Save risk report to file
    public boolean saveRiskReport(RiskReport riskReport) {
        String fileName = riskReport.getPatientId() + "_RiskReport.txt";
        String reportData = "Patient ID: " + riskReport.getPatientId() + "\n"
                          + "Risk Assessment Summary: " + riskReport.getSummary();
        return fileHandler.createFile(fileName, reportData);
    }

    // Retrieve risk report from file
    public RiskReport getRiskReport(String patientId) {
        String fileName = patientId + "_RiskReport.txt";
        if (!fileHandler.fileExists(fileName)) {
            System.out.println("Error: Risk report file not found for Patient ID: " + patientId);
            return null;
        }

        String reportData = fileHandler.readFile(fileName);
        if (reportData == null) {
            return null;
        }

        String[] lines = reportData.split("\n");
        String summary = lines[1].split(": ")[1];
        return new RiskReport(patientId, summary);
    }
}
