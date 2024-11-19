package com.example;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PatientView {

    public void start(Stage stage) {
        stage.setTitle("Patient View (Seeing the Results)");

        // Create a GridPane layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Patient ID input
        Label idPromptLabel = new Label("Enter Patient ID:");
        TextField idInputField = new TextField();
        Button loadButton = new Button("Load Data");

        // Title and Patient Name placeholder
        Label titleLabel = new Label("Hello <Patient Name>");
        titleLabel.setFont(Font.font("Arial", 16));

        // Agatston Score and Vessel Scores
        Label agatstonScoreLabel = new Label("The total Agatston CAC score:");
        TextField agatstonScoreField = new TextField();
        agatstonScoreField.setEditable(false);

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

        // Add nodes to grid
        grid.add(idPromptLabel, 0, 0);
        grid.add(idInputField, 1, 0);
        grid.add(loadButton, 1, 1);
        GridPane.setHalignment(loadButton, HPos.RIGHT);

        grid.add(titleLabel, 0, 2, 2, 1); // Span across two columns

        grid.add(agatstonScoreLabel, 0, 3);
        grid.add(agatstonScoreField, 1, 3);
        
        grid.add(lmLabel, 0, 4);
        grid.add(lmField, 1, 4);
        
        grid.add(ladLabel, 0, 5);
        grid.add(ladField, 1, 5);
        
        grid.add(lcxLabel, 0, 6);
        grid.add(lcxField, 1, 6);
        
        grid.add(rcaLabel, 0, 7);
        grid.add(rcaField, 1, 7);
        
        grid.add(pdaLabel, 0, 8);
        grid.add(pdaField, 1, 8);

        // Load data when button is pressed
        loadButton.setOnAction(e -> loadPatientData(
                idInputField.getText(),
                titleLabel,
                agatstonScoreField,
                lmField,
                ladField,
                lcxField,
                rcaField,
                pdaField
        ));

        Scene scene = new Scene(grid, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    private void loadPatientData(String patientID, Label titleLabel, TextField agatstonScoreField,
                                 TextField lmField, TextField ladField, TextField lcxField, TextField rcaField, TextField pdaField) {
        String infoFile = patientID + "_PatientInfo.txt";
        String scanFile = patientID + "CTResults.txt";

        boolean dataLoaded = false;

        // Load patient info
        try (BufferedReader infoReader = new BufferedReader(new FileReader(infoFile))) {
            String line;
            while ((line = infoReader.readLine()) != null) {
                if (line.startsWith("First Name:")) {
                    String firstName = line.split(": ")[1];
                    titleLabel.setText("Hello " + firstName);
                    dataLoaded = true;
                }
            }
        } catch (IOException e) {
            showError("Wrong patient ID. Patient information file not found.");
            return;
        }

        // Load scan data
        try (BufferedReader scanReader = new BufferedReader(new FileReader(scanFile))) {
            String line;
            while ((line = scanReader.readLine()) != null) {
                if (line.startsWith("Total Agatston CAC score:")) {
                    agatstonScoreField.setText(line.split(": ")[1]);
                } else if (line.startsWith("LM:")) {
                    lmField.setText(line.split(": ")[1]);
                } else if (line.startsWith("LAD:")) {
                    ladField.setText(line.split(": ")[1]);
                } else if (line.startsWith("LCX:")) {
                    lcxField.setText(line.split(": ")[1]);
                } else if (line.startsWith("RCA:")) {
                    rcaField.setText(line.split(": ")[1]);
                } else if (line.startsWith("PDA:")) {
                    pdaField.setText(line.split(": ")[1]);
                }
            }
            dataLoaded = true;
        } catch (IOException e) {
            showError("CT scan data not available for this patient.");
            return;
        }

        if (!dataLoaded) {
            showError("No data available for the entered patient ID.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.show();
    }
}
