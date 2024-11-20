package com.example.repositories;

import com.example.FileHandler;
import com.example.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {
    private final FileHandler fileHandler;

    // Constructor
    public AppointmentRepository(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    // Save appointment to file
    public boolean saveAppointment(Appointment appointment) {
        String fileName = appointment.getPatientId() + "_Appointments.txt";
        String appointmentData = "Appointment ID: " + appointment.getAppointmentId() + "\n"
                                + "Patient ID: " + appointment.getPatientId() + "\n"
                                + "Date: " + appointment.getDate() + "\n"
                                + "Time: " + appointment.getTime() + "\n";
        return fileHandler.appendToFile(fileName, appointmentData);
    }

    // Retrieve all appointments for a patient
    public List<Appointment> getAppointments(String patientId) {
        String fileName = patientId + "_Appointments.txt";
        if (!fileHandler.fileExists(fileName)) {
            System.out.println("Error: Appointments file not found for Patient ID: " + patientId);
            return null;
        }

        String appointmentsData = fileHandler.readFile(fileName);
        if (appointmentsData == null) {
            return null;
        }

        String[] entries = appointmentsData.split("\n\n");
        List<Appointment> appointments = new ArrayList<>();
        for (String entry : entries) {
            String[] lines = entry.split("\n");
            String appointmentId = lines[0].split(": ")[1];
            String date = lines[2].split(": ")[1];
            String time = lines[3].split(": ")[1];
            appointments.add(new Appointment(patientId, date, time));
        }
        return appointments;
    }
}
