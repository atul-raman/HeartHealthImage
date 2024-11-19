package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Heart Health Imaging and Recording System");

        // Title Text
        Text title = new Text("Welcome to Heart Health Imaging and Recording System");
        title.setFont(Font.font("Arial", 18));

        // Buttons with specific style
        Button patientIntakeButton = new Button("Patient Intake");
        styleButton(patientIntakeButton);

        Button techViewButton = new Button("CT Scan Tech View");
        styleButton(techViewButton);

        Button patientViewButton = new Button("Patient View");
        styleButton(patientViewButton);

        // Button Actions
        patientIntakeButton.setOnAction(e -> new ReceptionistView().start(new Stage()));
        techViewButton.setOnAction(e -> new TechnicianView().start(new Stage()));
        patientViewButton.setOnAction(e -> new PatientView().start(new Stage()));

        // Layout
        VBox layout = new VBox(20, title, patientIntakeButton, techViewButton, patientViewButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: white;");  // Set background color

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void styleButton(Button button) {
        button.setFont(Font.font("Arial", 16));
        button.setStyle("-fx-background-color: #4169E1; -fx-text-fill: white; -fx-padding: 10 20 10 20;");
        button.setPrefWidth(200);  // Set button width
    }

    public static void main(String[] args) {
        launch(args);
    }
}

