package com.tracker.financetrackerapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The main application
 */
public class MainApplication extends Application {
    /**
     * Start the application
     * @param primaryStage The primary stage
     * @throws Exception The exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseManager.initializeDatabase();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Personal Finance Tracker");
        primaryStage.show();
    }

    /**
     * The main method
     * @param args The arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}