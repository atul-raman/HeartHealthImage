package com.example;

import java.util.UUID;

public class Receptionist extends User {

    // Constructor
    public Receptionist(String id, String name, String password) {
        super(id, name, "Receptionist", password); // Call the parent User class constructor
    }

    // Collect patient information and create a new patient record
    public boolean intakePatient(String firstName, String lastName, String email, String phone, String healthHistory, String insuranceId, FileHandler fileHandler) {
        // Generate a unique 5-digit patient ID
        String patientId = generateUniquePatientId();

        // Create patient info data
        String patientInfoFile = patientId + "_PatientInfo.txt";
        String patientData = "Patient ID: " + patientId + "\n"
                           + "First Name: " + firstName + "\n"
                           + "Last Name: " + lastName + "\n"
                           + "Email: " + email + "\n"
                           + "Phone Number: " + phone + "\n"
                           + "Health History: " + healthHistory + "\n"
                           + "Insurance ID: " + insuranceId;

        // Save the patient info to a file
        if (fileHandler.createFile(patientInfoFile, patientData)) {
            System.out.println("Patient intake completed successfully. Patient ID: " + patientId);
            return true;
        } else {
            System.out.println("Error: Failed to save patient information.");
            return false;
        }
    }

    // Schedule a CT scan appointment for the patient
    public boolean scheduleAppointment(String patientId, String date, String time, FileHandler fileHandler) {
        // Check if the patient info file exists
        String patientInfoFile = patientId + "_PatientInfo.txt";
        if (!fileHandler.fileExists(patientInfoFile)) {
            System.out.println("Error: No patient information found for Patient ID: " + patientId);
            return false;
        }

        // Create appointment data
        String appointmentFile = patientId + "_Appointments.txt";
        String appointmentData = "Appointment ID: " + UUID.randomUUID() + "\n"
                               + "Patient ID: " + patientId + "\n"
                               + "Date: " + date + "\n"
                               + "Time: " + time;

        // Save the appointment data to a file
        if (fileHandler.appendToFile(appointmentFile, appointmentData)) {
            System.out.println("Appointment scheduled successfully for Patient ID: " + patientId);
            return true;
        } else {
            System.out.println("Error: Failed to save appointment information.");
            return false;
        }
    }

    // Generate a unique 5-digit patient ID
    private String generateUniquePatientId() {
        return String.format("%05d", (int) (Math.random() * 100000));
    }

    @Override
    public String toString() {
        return super.toString() + ", Role: Receptionist";
    }
}
