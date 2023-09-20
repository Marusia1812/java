package com.tracker.financetrackerapp;

import com.tracker.financetrackerapp.crud.CategoryCrud;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The settings controller
 */
public class SettingsController {

    @FXML
    private ListView<String> categoryListView;

    @FXML
    private TextField categoryField;

    private final CategoryCrud categoryCrud = new CategoryCrud();

    /**
     * Initialize the controller
     */
    @FXML
    public void initialize() {
        // Populate the categoryListView with existing categories from the database
        ObservableList<String> categories = FXCollections.observableArrayList(categoryCrud.getAllCategories());
        categoryListView.setItems(categories);
    }

    public static SettingsController instance;

    public SettingsController() {
        instance = this;
    }

    /**
     * Get the instance of the controller
     * @return The instance of the controller
     */
    public static SettingsController getInstance() {
        return instance;
    }

    /**
     * Show the add entry modal and add the entry
     */
    @FXML
    public void handleAddCategory() {
        String newCategory = categoryField.getText().trim();
        if (!newCategory.isEmpty() && !categoryListView.getItems().contains(newCategory)) {
            try {
                categoryCrud.addCategory(newCategory);
            } catch (Exception e) {
                Alerts.showError("Error", "Error adding category");
                return;
            }
            categoryListView.getItems().add(newCategory);
            categoryField.clear();
        }
    }

    /**
     * Show the edit entry modal and update the entry
     */
    @FXML
    public void handleEditCategory() {
        String selectedCategory = categoryListView.getSelectionModel().getSelectedItem();
        String updatedCategory = categoryField.getText().trim();
        if (selectedCategory != null && !updatedCategory.isEmpty() && !categoryListView.getItems().contains(updatedCategory)) {
            try {
                categoryCrud.updateCategory(selectedCategory, updatedCategory);
            } catch (Exception e) {
                Alerts.showError("Error", "Error updating category");
                return;
            }

            VisualizationController.getInstance().populateIncomePieChart();
            VisualizationController.getInstance().populateExpensePieChart();
            ExpenseController.getInstance().refreshExpenseListView();
            IncomeController.getInstance().refreshIncomeListView();

            categoryListView.getItems().set(categoryListView.getSelectionModel().getSelectedIndex(), updatedCategory);
            categoryField.clear();
        }
    }

    /**
     * Handle the delete entry button
     */
    @FXML
    public void handleDeleteCategory() {
        String selectedCategory = categoryListView.getSelectionModel().getSelectedItem();
        if (selectedCategory != null) {
            try {
                categoryCrud.deleteCategory(selectedCategory);
            } catch (Exception e) {
                Alerts.showError("Error", "Error deleting category");
                return;
            }
            categoryListView.getItems().remove(selectedCategory);
        }
    }

    /**
     * Refresh the category list
     */
    public void refreshCategoryList() {
        ObservableList<String> categories = FXCollections.observableArrayList(categoryCrud.getAllCategories());
        categoryListView.setItems(categories);
    }
}
