package com.example;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;

public class TechnicianView {
    private final GridPane root;
    private final Controller controller;

    public TechnicianView(Controller controller) {
        this.controller = controller;

        // Initialize the layout
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10); // Horizontal gap between columns
        root.setVgap(10); // Vertical gap between rows
        root.setPadding(new Insets(20, 20, 20, 20)); // Padding around the grid

        // Create UI elements
        Label titleLabel = new Label("Technician View");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label patientIdLabel = new Label("Patient ID:");
        TextField patientIdField = new TextField();

        Label totalCACLabel = new Label("The total Agatston CAC score:");
        TextField totalCACField = new TextField();

        Label vesselLabel = new Label("Vessel level Agatston CAC score:");
        Label lmLabel = new Label("LM:");
        TextField lmField = new TextField();
        Label ladLabel = new Label("LAD:");
        TextField ladField = new TextField();
        Label lcxLabel = new Label("LCX:");
        TextField lcxField = new TextField();
        Label rcaLabel = new Label("RCA:");
        TextField rcaField = new TextField();
        Label pdaLabel = new Label("PDA:");
        TextField pdaField = new TextField();

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #4a90e2; -fx-text-fill: white; -fx-font-size: 14px; "
                + "-fx-font-weight: bold; -fx-border-radius: 5; -fx-background-radius: 5; -fx-pref-height: 30px;");
        Label messageLabel = new Label();

        // Add elements to the grid layout
        root.add(titleLabel, 0, 0, 2, 1); // Title spans two columns
        root.add(patientIdLabel, 0, 1);
        root.add(patientIdField, 1, 1);
        root.add(totalCACLabel, 0, 2);
        root.add(totalCACField, 1, 2);
        root.add(vesselLabel, 0, 3, 2, 1); // Label spans two columns
        root.add(lmLabel, 0, 4);
        root.add(lmField, 1, 4);
        root.add(ladLabel, 0, 5);
        root.add(ladField, 1, 5);
        root.add(lcxLabel, 0, 6);
        root.add(lcxField, 1, 6);
        root.add(rcaLabel, 0, 7);
        root.add(rcaField, 1, 7);
        root.add(pdaLabel, 0, 8);
        root.add(pdaField, 1, 8);
        root.add(saveButton, 1, 9);
        root.add(messageLabel, 0, 10, 2, 1); // Message spans two columns

        // Handle saving CT scan data
        saveButton.setOnAction(e -> {
            try {
                String patientId = patientIdField.getText();
                int totalCACScore = Integer.parseInt(totalCACField.getText());
                int[] vesselScores = {
                        Integer.parseInt(lmField.getText()),
                        Integer.parseInt(ladField.getText()),
                        Integer.parseInt(lcxField.getText()),
                        Integer.parseInt(rcaField.getText()),
                        Integer.parseInt(pdaField.getText())
                };

                if (controller.recordCTScan(patientId, totalCACScore, vesselScores)) {
                    messageLabel.setText("Scan recorded successfully!");
                } else {
                    messageLabel.setText("Failed to record scan. Please try again.");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter valid numeric values for scores.");
            }
        });
    }

    // Get the root layout for the view
    public Pane getRoot() {
        return root;
    }
}
