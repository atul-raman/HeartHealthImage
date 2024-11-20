package com.example;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;

public class ReceptionistView {
    private final GridPane root;
    private final Controller controller;

    public ReceptionistView(Controller controller) {
        this.controller = controller;

        // Initialize the layout
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10); // Horizontal gap between columns
        root.setVgap(10); // Vertical gap between rows
        root.setPadding(new Insets(20, 20, 20, 20)); // Padding around the grid
        root.setStyle("-fx-background-color: #f9f9f9;"); // Light gray background

        // Create UI elements
        Label titleLabel = new Label("Patient Intake Form");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.setStyle("-fx-font-size: 14px;");
        TextField firstNameField = new TextField();
        firstNameField.setPrefWidth(250);

        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.setStyle("-fx-font-size: 14px;");
        TextField lastNameField = new TextField();
        lastNameField.setPrefWidth(250);

        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-font-size: 14px;");
        TextField emailField = new TextField();
        emailField.setPrefWidth(250);

        Label phoneLabel = new Label("Phone Number:");
        phoneLabel.setStyle("-fx-font-size: 14px;");
        TextField phoneField = new TextField();
        phoneField.setPrefWidth(250);

        Label healthHistoryLabel = new Label("Health History:");
        healthHistoryLabel.setStyle("-fx-font-size: 14px;");
        TextField healthHistoryField = new TextField();
        healthHistoryField.setPrefWidth(250);

        Label insuranceLabel = new Label("Insurance ID:");
        insuranceLabel.setStyle("-fx-font-size: 14px;");
        TextField insuranceField = new TextField();
        insuranceField.setPrefWidth(250);

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #4a90e2; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-width: 100px;");

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");

        // Add elements to the grid layout
        root.add(titleLabel, 0, 0, 2, 1); // Title spans two columns
        root.add(firstNameLabel, 0, 1);
        root.add(firstNameField, 1, 1);
        root.add(lastNameLabel, 0, 2);
        root.add(lastNameField, 1, 2);
        root.add(emailLabel, 0, 3);
        root.add(emailField, 1, 3);
        root.add(phoneLabel, 0, 4);
        root.add(phoneField, 1, 4);
        root.add(healthHistoryLabel, 0, 5);
        root.add(healthHistoryField, 1, 5);
        root.add(insuranceLabel, 0, 6);
        root.add(insuranceField, 1, 6);
        root.add(saveButton, 1, 7);
        root.add(messageLabel, 0, 8, 2, 1); // Message spans two columns

        // Handle saving patient information
        saveButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String healthHistory = healthHistoryField.getText();
            String insuranceId = insuranceField.getText();

            if (controller.addPatient(firstName, lastName, email, phone, healthHistory, insuranceId)) {
                messageLabel.setText("Patient information saved successfully!");
                messageLabel.setStyle("-fx-text-fill: green; -fx-font-size: 12px;");
            } else {
                messageLabel.setText("Failed to save patient information. Please try again.");
                messageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
            }
        });
    }

    // Get the root layout for the view
    public Pane getRoot() {
        return root;
    }
}
