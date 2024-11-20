package com.example.repositories;

import com.example.FileHandler;
import com.example.ScanResult;

public class CTScanRepository {
    private final FileHandler fileHandler;

    // Constructor
    public CTScanRepository(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    // Save CT scan data to file
    public boolean saveCTScanData(ScanResult scanResult) {
        String fileName = scanResult.getPatientId() + "_CTResults.txt";
        String scanData = "Patient ID: " + scanResult.getPatientId() + "\n"
                        + "Total CAC Score: " + scanResult.getTotalCACScore() + "\n"
                        + "LM: " + scanResult.getVesselScores()[0] + "\n"
                        + "LAD: " + scanResult.getVesselScores()[1] + "\n"
                        + "LCX: " + scanResult.getVesselScores()[2] + "\n"
                        + "RCA: " + scanResult.getVesselScores()[3] + "\n"
                        + "PDA: " + scanResult.getVesselScores()[4];
        return fileHandler.createFile(fileName, scanData);
    }

    // Retrieve CT scan data from file
    public ScanResult getCTScanData(String patientId) {
        String fileName = patientId + "_CTResults.txt";
        if (!fileHandler.fileExists(fileName)) {
            System.out.println("Error: CT scan results file not found for Patient ID: " + patientId);
            return null;
        }

        String scanData = fileHandler.readFile(fileName);
        if (scanData == null) {
            return null;
        }

        String[] lines = scanData.split("\n");
        int totalCACScore = Integer.parseInt(lines[1].split(": ")[1]);
        int[] vesselScores = new int[5];
        for (int i = 0; i < 5; i++) {
            vesselScores[i] = Integer.parseInt(lines[i + 2].split(": ")[1]);
        }

        return new ScanResult(patientId, totalCACScore, vesselScores);
    }
}
