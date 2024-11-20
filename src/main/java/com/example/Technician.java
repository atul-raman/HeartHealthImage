package com.example;

public class Technician extends User {

    // Constructor
    public Technician(String id, String name, String password) {
        super(id, name, "Technician", password); // Call the parent User class constructor
    }

    // Record scan data for a patient
    public boolean recordScanData(String patientId, int totalCACScore, int[] vesselScores, FileHandler fileHandler) {
        // Validate input
        if (vesselScores == null || vesselScores.length != 5) {
            System.out.println("Error: Vessel scores must be provided for all five vessels (LM, LAD, LCX, RCA, PDA).");
            return false;
        }

        // Check if the patient file exists
        String patientInfoFile = patientId + "_PatientInfo.txt";
        if (!fileHandler.fileExists(patientInfoFile)) {
            System.out.println("Error: No patient information found for Patient ID: " + patientId);
            return false;
        }

        // Create CT scan result data
        String scanResultsFile = patientId + "_CTResults.txt";
        String scanData = "Patient ID: " + patientId + "\n"
                        + "Total CAC Score: " + totalCACScore + "\n"
                        + "LM: " + vesselScores[0] + "\n"
                        + "LAD: " + vesselScores[1] + "\n"
                        + "LCX: " + vesselScores[2] + "\n"
                        + "RCA: " + vesselScores[3] + "\n"
                        + "PDA: " + vesselScores[4];

        // Save the scan data to a file
        if (fileHandler.createFile(scanResultsFile, scanData)) {
            System.out.println("CT scan results recorded successfully for Patient ID: " + patientId);
            return true;
        } else {
            System.out.println("Error: Failed to save CT scan results for Patient ID: " + patientId);
            return false;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Role: Technician";
    }
}
