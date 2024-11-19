package com.example;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ReceptionistView {

    public void start(Stage stage) {
        stage.setTitle("Patient Intake Form");

        // Create a GridPane layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Labels and TextFields for patient details
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();
        Label healthHistoryLabel = new Label("Health History:");
        TextField healthHistoryField = new TextField();
        Label insuranceIDLabel = new Label("Insurance ID:");
        TextField insuranceIDField = new TextField();

        // Save Button
        Button saveButton = new Button("Save");
        styleButton(saveButton);

        saveButton.setOnAction(e -> {
            if (validateInputs(firstNameField, lastNameField, emailField, phoneField, healthHistoryField, insuranceIDField)) {
                String patientID = generatePatientID();
                savePatientInfo(patientID, firstNameField.getText(), lastNameField.getText(), emailField.getText(),
                        phoneField.getText(), healthHistoryField.getText(), insuranceIDField.getText());
            }
        });

        // Add nodes to grid
        grid.add(firstNameLabel, 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(lastNameLabel, 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(emailLabel, 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(phoneLabel, 0, 3);
        grid.add(phoneField, 1, 3);
        grid.add(healthHistoryLabel, 0, 4);
        grid.add(healthHistoryField, 1, 4);
        grid.add(insuranceIDLabel, 0, 5);
        grid.add(insuranceIDField, 1, 5);
        grid.add(saveButton, 1, 6);
        GridPane.setHalignment(saveButton, HPos.RIGHT); // Align Save button to the right

        Scene scene = new Scene(grid, 500, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #4169E1; -fx-text-fill: white; -fx-padding: 5 15 5 15;");
    }

    private boolean validateInputs(TextField... fields) {
        for (TextField field : fields) {
            if (field.getText().isEmpty()) {
                showAlert("Please fill out all fields.");
                return false;
            }
        }
        return true;
    }

    private void savePatientInfo(String patientID, String firstName, String lastName, String email, String phone,
                                 String healthHistory, String insuranceID) {
        String fileName = patientID + "_PatientInfo.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Patient ID: " + patientID + "\n");
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Phone Number: " + phone + "\n");
            writer.write("Health History: " + healthHistory + "\n");
            writer.write("Insurance ID: " + insuranceID + "\n");
            showAlert("Patient record saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("An error occurred while saving the patient information.");
        }
    }

    private String generatePatientID() {
        Random random = new Random();
        int patientID = 10000 + random.nextInt(90000); // Generate a random 5-digit ID
        return String.valueOf(patientID);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}
