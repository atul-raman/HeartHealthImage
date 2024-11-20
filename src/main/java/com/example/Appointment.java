package com.example;

import java.util.UUID;

public class Appointment {
    private String appointmentId; // Unique identifier for the appointment
    private String patientId;     // Associated patient ID
    private String date;          // Appointment date
    private String time;          // Appointment time

    // Constructor
    public Appointment(String patientId, String date, String time) {
        this.appointmentId = UUID.randomUUID().toString(); // Generate a unique appointment ID
        this.patientId = patientId;
        this.date = date;
        this.time = time;
    }

    // Getters and Setters
    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    // Save the appointment to a file
    public boolean saveAppointment(FileHandler fileHandler) {
        String appointmentFile = patientId + "_Appointments.txt";
        String appointmentData = "Appointment ID: " + appointmentId + "\n"
                               + "Patient ID: " + patientId + "\n"
                               + "Date: " + date + "\n"
                               + "Time: " + time + "\n";

        // Append appointment data to the patient's appointment file
        if (fileHandler.appendToFile(appointmentFile, appointmentData)) {
            System.out.println("Appointment saved successfully for Patient ID: " + patientId);
            return true;
        } else {
            System.out.println("Error: Failed to save appointment for Patient ID: " + patientId);
            return false;
        }
    }

    // Retrieve appointment details from a file
    public static String getAppointments(String patientId, FileHandler fileHandler) {
        String appointmentFile = patientId + "_Appointments.txt";

        // Check if the file exists
        if (!fileHandler.fileExists(appointmentFile)) {
            System.out.println("Error: No appointments found for Patient ID: " + patientId);
            return null;
        }

        // Read and return the file content
        return fileHandler.readFile(appointmentFile);
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + "\n"
             + "Patient ID: " + patientId + "\n"
             + "Date: " + date + "\n"
             + "Time: " + time;
    }
}
