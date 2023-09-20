package com.tracker.financetrackerapp;

import com.tracker.financetrackerapp.crud.IncomeCrud;
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
 * The income controller
 */
public class IncomeController {
    @FXML
    private ListView<IncomeEntry> incomeListView;
    private final IncomeCrud incomeCrud = new IncomeCrud();

    public static IncomeController instance;

    public IncomeController() {
        instance = this;
    }

    /**
     * Get the instance of the controller
     * @return The instance of the controller
     */
    public static IncomeController getInstance() {
        return instance;
    }

    /**
     * Initialize the controller
     */
    @FXML
    public void initialize() {
        // Populate the incomeListView with existing income entries from the database
        List<IncomeEntry> incomeEntries = incomeCrud.getAllIncomeEntries();
        for (IncomeEntry entry : incomeEntries) {
            incomeListView.getItems().add(entry);
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
            controller.setEntryType(EntryType.INCOME);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            // Refresh the incomeListView after adding an entry
            incomeListView.getItems().clear();
            List<IncomeEntry> updatedIncomeEntries = incomeCrud.getAllIncomeEntries();
            for (IncomeEntry entry : updatedIncomeEntries) {
                incomeListView.getItems().add(entry);
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
        IncomeEntry selectedRow = incomeListView.getSelectionModel().getSelectedItem();

        if (selectedRow != null) {
            try {
                incomeCrud.deleteIncome(selectedRow.getId());
            } catch (Exception e) {
               Alerts.showError("Error", "Error deleting income entry");
            }
            incomeListView.getItems().remove(selectedRow);
            VisualizationController.getInstance().populateIncomePieChart();
        }
    }

    /**
     * Refresh the incomeListView
     */
    public void refreshIncomeListView() {
        incomeListView.getItems().clear();
        List<IncomeEntry> updatedIncomeEntries = incomeCrud.getAllIncomeEntries();
        for (IncomeEntry entry : updatedIncomeEntries) {
            incomeListView.getItems().add(entry);
        }
    }
}
