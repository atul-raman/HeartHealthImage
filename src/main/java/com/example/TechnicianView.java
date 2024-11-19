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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TechnicianView {

    public void start(Stage stage) {
        stage.setTitle("CT Scan Technician View");

        // Create a GridPane layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Labels and TextFields for Technician data input
        Label patientIDLabel = new Label("Patient ID:");
        TextField patientIDField = new TextField();

        Label agatstonScoreLabel = new Label("The total Agatston CAC score:");
        TextField agatstonScoreField = new TextField();

        Label vesselScoreLabel = new Label("Vessel level Agatston CAC score");
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

        // Save Button
        Button saveButton = new Button("Save");
        styleButton(saveButton);

        saveButton.setOnAction(e -> {
            String patientID = patientIDField.getText();
            if (validateInputs(patientIDField, agatstonScoreField, lmField, ladField, lcxField, rcaField, pdaField)) {
                if (patientExists(patientID)) {
                    saveScanData(patientID, agatstonScoreField.getText(),
                            lmField.getText(), ladField.getText(), lcxField.getText(), rcaField.getText(), pdaField.getText());
                } else {
                    showAlert("Patient ID does not exist.");
                }
            }
        });

        // Add nodes to grid
        grid.add(patientIDLabel, 0, 0);
        grid.add(patientIDField, 1, 0);

        grid.add(agatstonScoreLabel, 0, 1);
        grid.add(agatstonScoreField, 1, 1);

        grid.add(vesselScoreLabel, 0, 2, 2, 1); // Span across two columns for header
        grid.add(lmLabel, 0, 3);
        grid.add(lmField, 1, 3);

        grid.add(ladLabel, 0, 4);
        grid.add(ladField, 1, 4);

        grid.add(lcxLabel, 0, 5);
        grid.add(lcxField, 1, 5);

        grid.add(rcaLabel, 0, 6);
        grid.add(rcaField, 1, 6);

        grid.add(pdaLabel, 0, 7);
        grid.add(pdaField, 1, 7);

        grid.add(saveButton, 1, 8);
        GridPane.setHalignment(saveButton, HPos.RIGHT); // Align Save button to the right

        Scene scene = new Scene(grid, 500, 500);
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

    private boolean patientExists(String patientID) {
        File patientFile = new File(patientID + "_PatientInfo.txt");
        return patientFile.exists();
    }

    private void saveScanData(String patientID, String agatstonScore, String lm, String lad, String lcx, String rca, String pda) {
        String fileName = patientID + "CTResults.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Patient ID: " + patientID + "\n");
            writer.write("Total Agatston CAC score: " + agatstonScore + "\n");
            writer.write("LM: " + lm + "\n");
            writer.write("LAD: " + lad + "\n");
            writer.write("LCX: " + lcx + "\n");
            writer.write("RCA: " + rca + "\n");
            writer.write("PDA: " + pda + "\n");
            showAlert("CT scan data saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("An error occurred while saving the CT scan data.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}
