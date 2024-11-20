package com.example;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {

    private Controller controller;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the controller with the primary stage
        controller = new Controller(primaryStage);

        // Set up the main view
        MainView mainView = new MainView(controller);
        Scene scene = new Scene(mainView.getRoot(), 400, 400); // Adjusted size for better alignment

        // Configure and display the primary stage
        primaryStage.setTitle("Heart Health Imaging and Recording System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
