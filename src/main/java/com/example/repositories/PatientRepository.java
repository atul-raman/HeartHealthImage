package com.example.repositories;

import com.example.FileHandler;
import com.example.Patient;

public class PatientRepository {
    private final FileHandler fileHandler;

    // Constructor
    public PatientRepository(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    // Save patient data to file
    public boolean savePatient(Patient patient) {
        String fileName = patient.getPatientId() + "_PatientInfo.txt";
        String patientData = "Patient ID: " + patient.getPatientId() + "\n"
                           + "Name: " + patient.getName() + "\n"
                           + "Health History: " + patient.getHealthHistory() + "\n"
                           + "Insurance ID: " + patient.getInsuranceId();
        return fileHandler.createFile(fileName, patientData);
    }

    // Retrieve patient data from file
    public Patient getPatient(String patientId) {
        String fileName = patientId + "_PatientInfo.txt";
        if (!fileHandler.fileExists(fileName)) {
            System.out.println("Error: Patient information file not found for Patient ID: " + patientId);
            return null;
        }

        String patientData = fileHandler.readFile(fileName);
        if (patientData == null) {
            return null;
        }

        String[] lines = patientData.split("\n");
        String name = lines[1].split(": ")[1];
        String healthHistory = lines[2].split(": ")[1];
        String insuranceId = lines[3].split(": ")[1];
        return new Patient("P_" + patientId, name, "", patientId, healthHistory, insuranceId);
    }
}
