package com.example;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;

public class DoctorView {
    private final VBox root;
    private final Controller controller;

    public DoctorView(Controller controller) {
        this.controller = controller;

        // Initialize the layout
        root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        // Create UI elements
        Label titleLabel = new Label("Doctor View");
        TextField patientIdField = new TextField();
        patientIdField.setPromptText("Enter Patient ID");

        Button generateReportButton = new Button("Generate Risk Report");
        Label messageLabel = new Label();

        // Handle generating a risk report
        generateReportButton.setOnAction(e -> {
            String patientId = patientIdField.getText();

            if (controller.generateAndSaveRiskReport(patientId)) {
                messageLabel.setText("Risk report generated successfully!");
            } else {
                messageLabel.setText("Failed to generate risk report. Please try again.");
            }
        });

        // Add elements to the layout
        root.getChildren().addAll(titleLabel, patientIdField, generateReportButton, messageLabel);
    }

    // Get the root layout for the view
    public Pane getRoot() {
        return root;
    }
}
