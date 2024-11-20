package com.example;

public class ScanResult {
    private String patientId;    // Associated patient ID
    private int totalCACScore;   // Total Agatston score
    private int[] vesselScores;  // Array containing scores for LM, LAD, LCX, RCA, PDA

    // Constructor
    public ScanResult(String patientId, int totalCACScore, int[] vesselScores) {
        this.patientId = patientId;
        this.totalCACScore = totalCACScore;

        // Validate the vesselScores array size
        if (vesselScores == null || vesselScores.length != 5) {
            throw new IllegalArgumentException("Vessel scores must contain exactly 5 values for LM, LAD, LCX, RCA, PDA.");
        }
        this.vesselScores = vesselScores;
    }

    // Getters
    public String getPatientId() {
        return patientId;
    }

    public int getTotalCACScore() {
        return totalCACScore;
    }

    public int[] getVesselScores() {
        return vesselScores;
    }

    // Setters (if needed)
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setTotalCACScore(int totalCACScore) {
        this.totalCACScore = totalCACScore;
    }

    public void setVesselScores(int[] vesselScores) {
        if (vesselScores == null || vesselScores.length != 5) {
            throw new IllegalArgumentException("Vessel scores must contain exactly 5 values for LM, LAD, LCX, RCA, PDA.");
        }
        this.vesselScores = vesselScores;
    }

    // Convert the scan result into a formatted string for saving to a file
    @Override
    public String toString() {
        return "Patient ID: " + patientId + "\n"
             + "Total CAC Score: " + totalCACScore + "\n"
             + "LM: " + vesselScores[0] + "\n"
             + "LAD: " + vesselScores[1] + "\n"
             + "LCX: " + vesselScores[2] + "\n"
             + "RCA: " + vesselScores[3] + "\n"
             + "PDA: " + vesselScores[4];
    }
}
