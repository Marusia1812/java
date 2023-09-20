package com.tracker.financetrackerapp;

import javafx.scene.control.Alert;

/**
 * The alerts
 */
public class Alerts {
    /**
     * Show an error alert
     * @param title The title of the alert
     * @param message The message of the alert
     */
    public static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
