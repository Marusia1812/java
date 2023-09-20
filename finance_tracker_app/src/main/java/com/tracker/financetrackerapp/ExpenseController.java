package com.tracker.financetrackerapp;

import com.tracker.financetrackerapp.crud.ExpenseCrud;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.List;

/**
 * The expense controller
 */
public class ExpenseController {
    @FXML
    private ListView<ExpenseEntry> expenseListView;
    private final ExpenseCrud expenseCrud = new ExpenseCrud();

    public static ExpenseController instance;

    public ExpenseController() {
        instance = this;
    }

    /**
     * Get the instance of the controller
     * @return The instance of the controller
     */
    public static ExpenseController getInstance() {
        return instance;
    }

    /**
     * Initialize the controller
     */
    @FXML
    public void initialize() {
        // Populate the expenseListView with existing expense entries from the database
        List<ExpenseEntry> expenseEntries = expenseCrud.getAllExpenseEntries();
        for (ExpenseEntry entry : expenseEntries) {
            expenseListView.getItems().add(entry);
        }
    }

    /**
     * Show the add entry modal
     */
    @FXML
    public void showAddEntryModal() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddEntryModal.fxml"));
            Parent parent = fxmlLoader.load();

            // Get the controller and refresh the category list
            AddEntryModalController controller = fxmlLoader.getController();
            controller.refreshCategoryBox();
            controller.setEntryType(EntryType.EXPENSE);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            // Refresh the expenseListView after adding an entry
            expenseListView.getItems().clear();
            List<ExpenseEntry> updatedExpenseEntries = expenseCrud.getAllExpenseEntries();
            for (ExpenseEntry entry : updatedExpenseEntries) {
                expenseListView.getItems().add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle the delete entry button
     */
    @FXML
    public void handleDeleteEntry() {
        ExpenseEntry selectedExpense = expenseListView.getSelectionModel().getSelectedItem();

        if (selectedExpense != null) {
            try {
                expenseCrud.deleteExpense(selectedExpense.getId());
            } catch (Exception e) {
                Alerts.showError("Error", "Error deleting expense");
                return;
            }
            expenseListView.getItems().remove(selectedExpense);
            VisualizationController.getInstance().populateExpensePieChart();
        }
    }

    /**
     * Refresh the expenseListView
     */
    public void refreshExpenseListView() {
        expenseListView.getItems().clear();
        List<ExpenseEntry> updatedExpenseEntries = expenseCrud.getAllExpenseEntries();
        for (ExpenseEntry entry : updatedExpenseEntries) {
            expenseListView.getItems().add(entry);
        }
    }

}
