package com.tracker.financetrackerapp;

import com.tracker.financetrackerapp.crud.CategoryCrud;
import com.tracker.financetrackerapp.crud.ExpenseCrud;
import com.tracker.financetrackerapp.crud.IncomeCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;

/**
 * The add entry modal controller
 */
public class AddEntryModalController {

    @FXML
    private TextField amountField;

    @FXML
    private TextField descriptionField;

    @FXML
    private ComboBox<String> categoryBox;

    @FXML
    private DatePicker datePicker;

    private final CategoryCrud categoryCrud = new CategoryCrud();

    private EntryType currentEntryType;

    /**
     * Initialize the controller
     */

    public void initialize() {
        refreshCategoryBox();
    }

    /**
     * Set the entry type
     * @param type The entry type
     */
    public void setEntryType(EntryType type) {
        this.currentEntryType = type;
    }

    /**
     * Refresh the category box
     */
    public void refreshCategoryBox() {
        // Fetch categories from the database
        ObservableList<String> categories = FXCollections.observableArrayList(categoryCrud.getAllCategories());
        categoryBox.setItems(categories);
    }

    /**
     * Handle the add entry button
     */
    @FXML
    public void handleAddEntry() {
        if (amountField.getText().isEmpty()) {
            Alerts.showError("Error", "Amount cannot be empty");
            return;
        }

        if (descriptionField.getText().isEmpty()) {
            Alerts.showError("Error", "Description cannot be empty");
            return;
        }

        if (categoryBox.getValue() == null || categoryBox.getValue().isEmpty()) {
            Alerts.showError("Error", "Category cannot be empty");
            return;
        }

        if (datePicker.getValue() == null) {
            datePicker.setValue(LocalDate.now());
        }

        double amount;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            Alerts.showError("Error", "Amount must be a number");
            return;
        }

        String description = descriptionField.getText();
        String category = categoryBox.getValue();

        if (!categoryCrud.categoryExists(category)) {
            try {
                categoryCrud.addCategory(category);
            } catch (Exception e) {
                Alerts.showError("Error", "Error adding category");
                return;
            }
        }

        if (currentEntryType == EntryType.INCOME) {
            IncomeCrud incomeCrud = new IncomeCrud();
            try {
                incomeCrud.addIncome(amount, description, category, Date.valueOf(datePicker.getValue()));
            } catch (Exception e) {
                Alerts.showError("Error", "Error adding income");
                return;
            }
            VisualizationController.getInstance().populateIncomePieChart();
        } else if (currentEntryType == EntryType.EXPENSE) {
            ExpenseCrud expenseCrud = new ExpenseCrud();
            try {
                expenseCrud.addExpense(amount, description, category, Date.valueOf(datePicker.getValue()));
            } catch (Exception e) {
                Alerts.showError("Error", "Error adding expense");
                return;
            }
            VisualizationController.getInstance().populateExpensePieChart();
        }

        SettingsController.getInstance().refreshCategoryList();

        Stage stage = (Stage) amountField.getScene().getWindow();
        stage.close();
    }
}

