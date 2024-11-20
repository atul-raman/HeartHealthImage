package com.example;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;

public class MainView {
    private final VBox root;
    private final Controller controller;

    public MainView(Controller controller) {
        this.controller = controller;

        // Initialize the root layout
        root = new VBox(30); // Increased spacing
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #ffffff;");

        // Create UI elements
        Label welcomeLabel = new Label("Welcome to Heart Health Imaging and Recording System");
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333;");

        // Create buttons for navigation
        Button patientIntakeButton = new Button("Patient Intake");
        Button ctScanTechViewButton = new Button("CT Scan Tech View");
        Button patientViewButton = new Button("Patient View");

        // Style buttons
        String buttonStyle = "-fx-background-color: #4a90e2; -fx-text-fill: white; -fx-font-size: 16px; "
                + "-fx-font-weight: bold; -fx-border-radius: 5; -fx-background-radius: 5; -fx-pref-height: 50px;";
        patientIntakeButton.setStyle(buttonStyle);
        ctScanTechViewButton.setStyle(buttonStyle);
        patientViewButton.setStyle(buttonStyle);

        // Set button dimensions
        patientIntakeButton.setMinWidth(250);
        ctScanTechViewButton.setMinWidth(250);
        patientViewButton.setMinWidth(250);

        // Add event handlers
        patientIntakeButton.setOnAction(e -> controller.showReceptionistView());
        ctScanTechViewButton.setOnAction(e -> controller.showTechnicianView());
        patientViewButton.setOnAction(e -> controller.showPatientView());

        // Add elements to the layout
        root.getChildren().addAll(welcomeLabel, patientIntakeButton, ctScanTechViewButton, patientViewButton);
    }

    public Pane getRoot() {
        return root;
    }
}
