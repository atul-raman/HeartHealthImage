package com.example;

import com.example.repositories.*;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Controller {
    private Stage mainStage;
    private FileHandler fileHandler;

    // Repositories
    private PatientRepository patientRepository;
    private CTScanRepository ctScanRepository;
    private RiskReportRepository riskReportRepository;
    private AppointmentRepository appointmentRepository;

    // Current user
    private User currentUser;

    // Constructor
    public Controller(Stage stage) {
        this.mainStage = stage;
        this.fileHandler = new FileHandler();

        // Initialize repositories
        this.patientRepository = new PatientRepository(fileHandler);
        this.ctScanRepository = new CTScanRepository(fileHandler);
        this.riskReportRepository = new RiskReportRepository(fileHandler);
        this.appointmentRepository = new AppointmentRepository(fileHandler);
    }
    

    // Authenticate user and set the current user
    public boolean authenticateUser(String id, String password, String role) {
        // For simplicity, mock authentication
        if (id.equals("admin") && password.equals("password")) {
            currentUser = new User(id, "Admin", role, password);
            return true;
        }
        System.out.println("Authentication failed!");
        return false;
    }

    // Role-Based Navigation
    public void navigateToRoleBasedView() {
        if (currentUser == null) {
            System.out.println("No user is logged in.");
            return;
        }

        switch (currentUser.getRole()) {
            case "Receptionist":
                showReceptionistView();
                break;
            case "Technician":
                showTechnicianView();
                break;
            case "Doctor":
                showDoctorView();
                break;
            case "Patient":
                showPatientView();
                break;
            default:
                System.out.println("Invalid role. Cannot navigate.");
                break;
        }
    }

    // Views
    public void showMainView() {
        MainView mainView = new MainView(this);
        mainStage.setScene(new Scene(mainView.getRoot(), 400, 300));
        mainStage.setTitle("Heart Health Imaging System - Login");
        mainStage.show();
    }

     // Open Receptionist View as a Modal
    public void showReceptionistView() {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(mainStage);
        modalStage.setTitle("Receptionist View");
        // Position the modal slightly away from the current screen
        modalStage.setX(mainStage.getX() + 50); // 50px offset from main stage
        modalStage.setY(mainStage.getY() + 50); // 50px offset from main stage

        ReceptionistView receptionistView = new ReceptionistView(this);
        Scene scene = new Scene(receptionistView.getRoot(), 400, 400);

        modalStage.setScene(scene);
        modalStage.showAndWait(); // Blocks input to main window until this stage is closed
    }

    // Open Technician View as a Modal
    public void showTechnicianView() {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(mainStage);
        modalStage.setTitle("Technician View");
        // Position the modal slightly away from the current screen
        modalStage.setX(mainStage.getX() + 50); // 50px offset from main stage
        modalStage.setY(mainStage.getY() + 50); // 50px offset from main stage

        TechnicianView technicianView = new TechnicianView(this);
        Scene scene = new Scene(technicianView.getRoot(), 400, 400);

        modalStage.setScene(scene);
        modalStage.showAndWait(); // Blocks input to main window until this stage is closed
    }

    // Open Doctor View as a Modal
    public void showDoctorView() {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(mainStage);
        modalStage.setTitle("Doctor View");
        // Position the modal slightly away from the current screen
        modalStage.setX(mainStage.getX() + 50); // 50px offset from main stage
        modalStage.setY(mainStage.getY() + 50); // 50px offset from main stage

        DoctorView doctorView = new DoctorView(this);
        Scene scene = new Scene(doctorView.getRoot(), 400, 400);

        modalStage.setScene(scene);
        modalStage.showAndWait(); // Blocks input to main window until this stage is closed
    }

    // Open Patient View as a Modal
    public void showPatientView() {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(mainStage);
        modalStage.setTitle("Patient View");
        // Position the modal slightly away from the current screen
        modalStage.setX(mainStage.getX() + 50); // 50px offset from main stage
        modalStage.setY(mainStage.getY() + 50); // 50px offset from main stage

        PatientView patientView = new PatientView(this);
        Scene scene = new Scene(patientView.getRoot(), 400, 400);

        modalStage.setScene(scene);
        modalStage.showAndWait(); // Blocks input to main window until this stage is closed
    }

    // Patient Operations
    // Patient Operations
    public boolean addPatient(String firstName, String lastName, String email, String phone, String healthHistory, String insuranceId) {
    Receptionist receptionist = new Receptionist("R001", "Receptionist", "password");
    return receptionist.intakePatient(firstName, lastName, email, phone, healthHistory, insuranceId, fileHandler);
}


    public Patient getPatient(String patientId) {
        return patientRepository.getPatient(patientId);
    }
    

    // CT Scan Operations
    public boolean recordCTScan(String patientId, int totalCACScore, int[] vesselScores) {
        Technician technician = new Technician("T001", "Technician", "password");
        return technician.recordScanData(patientId, totalCACScore, vesselScores, fileHandler);
    }

    public ScanResult getCTScan(String patientId) {
        return ctScanRepository.getCTScanData(patientId);
    }

    // Risk Report Operations
    public boolean generateAndSaveRiskReport(String patientId) {
        ScanResult scanResult = ctScanRepository.getCTScanData(patientId);
        if (scanResult == null) {
            System.out.println("No scan data found for Patient ID: " + patientId);
            return false;
        }

        Doctor doctor = new Doctor("D001", "Doctor", "password");
        RiskReport riskReport = doctor.analyzeScanResults(scanResult);
        return riskReportRepository.saveRiskReport(riskReport);
    }

    public RiskReport getRiskReport(String patientId) {
        return riskReportRepository.getRiskReport(patientId);
    }

    // Appointment Operations
    public boolean scheduleAppointment(String patientId, String date, String time) {
        Receptionist receptionist = new Receptionist("R001", "Receptionist", "password");
        return receptionist.scheduleAppointment(patientId, date, time, fileHandler);
    }

    public String getAppointments(String patientId) {
        return Appointment.getAppointments(patientId, fileHandler);
    }
}
