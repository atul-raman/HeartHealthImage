package com.example;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;

public class PatientView {
    private final GridPane root;
    private final Controller controller;

    public PatientView(Controller controller) {
        this.controller = controller;

        // Initialize the layout
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10); // Horizontal gap between columns
        root.setVgap(10); // Vertical gap between rows
        root.setPadding(new Insets(20, 20, 20, 20)); // Padding around the grid

        // Create UI elements
        Label titleLabel = new Label("Patient View (Seeing the Results)");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label helloLabel = new Label("Hello <Patient Name>");
        helloLabel.setStyle("-fx-font-size: 14px;");

        Label totalCACLabel = new Label("The total Agatston CAC score:");
        TextField totalCACField = new TextField();
        totalCACField.setEditable(false);

        Label vesselLabel = new Label("Vessel level Agatston CAC score:");
        Label lmLabel = new Label("LM:");
        TextField lmField = new TextField();
        lmField.setEditable(false);
        Label ladLabel = new Label("LAD:");
        TextField ladField = new TextField();
        ladField.setEditable(false);
        Label lcxLabel = new Label("LCX:");
        TextField lcxField = new TextField();
        lcxField.setEditable(false);
        Label rcaLabel = new Label("RCA:");
        TextField rcaField = new TextField();
        rcaField.setEditable(false);
        Label pdaLabel = new Label("PDA:");
        TextField pdaField = new TextField();
        pdaField.setEditable(false);

        TextField patientIdField = new TextField();
        patientIdField.setPromptText("Enter Patient ID");

        Button viewButton = new Button("View Results");
        viewButton.setStyle("-fx-background-color: #4a90e2; -fx-text-fill: white; -fx-font-size: 14px; "
                + "-fx-font-weight: bold; -fx-border-radius: 5; -fx-background-radius: 5; -fx-pref-height: 30px;");
        Label messageLabel = new Label();

        // Add elements to the grid layout
        root.add(titleLabel, 0, 0, 2, 1); // Title spans two columns
        root.add(helloLabel, 0, 1, 2, 1); // Greeting spans two columns
        root.add(patientIdField, 0, 2, 2, 1); // Patient ID input spans two columns
        root.add(totalCACLabel, 0, 3);
        root.add(totalCACField, 1, 3);
        root.add(vesselLabel, 0, 4, 2, 1); // Vessel label spans two columns
        root.add(lmLabel, 0, 5);
        root.add(lmField, 1, 5);
        root.add(ladLabel, 0, 6);
        root.add(ladField, 1, 6);
        root.add(lcxLabel, 0, 7);
        root.add(lcxField, 1, 7);
        root.add(rcaLabel, 0, 8);
        root.add(rcaField, 1, 8);
        root.add(pdaLabel, 0, 9);
        root.add(pdaField, 1, 9);
        root.add(viewButton, 1, 10);
        root.add(messageLabel, 0, 11, 2, 1); // Message spans two columns

        // Handle viewing results
        viewButton.setOnAction(e -> {
            String patientId = patientIdField.getText();
            if (patientId == null || patientId.isEmpty()) {
                messageLabel.setText("Please enter a valid Patient ID.");
                return;
            }

            // Attempt to load patient info
            Patient patient = controller.getPatient(patientId);
            if (patient == null) {
                messageLabel.setText("Wrong Patient ID. No such patient exists.");
                return;
            }
            helloLabel.setText("Hello " + patient.getName());

            // Attempt to load CT scan data
            ScanResult scanResult = controller.getCTScan(patientId);
            if (scanResult != null) {
                totalCACField.setText(String.valueOf(scanResult.getTotalCACScore()));
                int[] vesselScores = scanResult.getVesselScores();
                lmField.setText(String.valueOf(vesselScores[0]));
                ladField.setText(String.valueOf(vesselScores[1]));
                lcxField.setText(String.valueOf(vesselScores[2]));
                rcaField.setText(String.valueOf(vesselScores[3]));
                pdaField.setText(String.valueOf(vesselScores[4]));
                messageLabel.setText("Results loaded successfully!");
            } else {
                messageLabel.setText("No CT Scan data available for Patient ID: " + patientId);
            }
        });
    }

    // Get the root layout for the view
    public Pane getRoot() {
        return root;
    }
}
