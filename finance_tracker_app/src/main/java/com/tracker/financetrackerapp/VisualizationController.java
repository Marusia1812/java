package com.tracker.financetrackerapp;

import com.tracker.financetrackerapp.crud.ExpenseCrud;
import com.tracker.financetrackerapp.crud.IncomeCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;

import java.time.LocalDate;
import java.util.Map;

/**
 * The visualization controller
 */
public class VisualizationController {
    @FXML
    private PieChart incomePieChart;

    @FXML
    private PieChart expensePieChart;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private ComboBox<String> yearComboBox;


    /**
     * Initialize the controller
     */
    @FXML
    public void initialize() {
        populateIncomePieChart();
        populateExpensePieChart();

        monthComboBox.getItems().addAll(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
        );

        int currentYear = LocalDate.now().getYear();
        for (int year = 2000; year <= currentYear + 50; year++) {
            yearComboBox.getItems().add(String.valueOf(year));
        }
    }

    private static VisualizationController instance;

    public VisualizationController() {
        instance = this;
    }

    /**
     * Get the instance of the controller
     * @return The instance of the controller
     */
    public static VisualizationController getInstance() {
        return instance;
    }

    /**
     * Populate the income pie chart
     */
    public void populateIncomePieChart() {
        IncomeCrud incomeCrud = new IncomeCrud();
        Map<String, Double> incomeByCategory = incomeCrud.getIncomeByCategory();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : incomeByCategory.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        incomePieChart.setData(pieChartData);
    }

    /**
     * Populate the expense pie chart
     */
    public void populateExpensePieChart() {
        ExpenseCrud expenseCrud = new ExpenseCrud();
        Map<String, Double> expenseByCategory = expenseCrud.getExpenseByCategory();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : expenseByCategory.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        expensePieChart.setData(pieChartData);
    }

    /**
     * Handle the filter button
     */
    @FXML
    public void handleFilterButton() {
        String month = monthComboBox.getValue();
        String year = yearComboBox.getValue();

        if (month == null && year == null) {
            populateIncomePieChart();
            populateExpensePieChart();
            return;
        } else if (month == null || year == null) {
            Alerts.showError("Error", "Please select both month and year");
            return;
        }


        int monthInt;
        try {
            monthInt = Integer.parseInt(month);
            if (monthInt < 1 || monthInt > 12) {
                Alerts.showError("Error", "Invalid month");
                return;
            }
        } catch (NumberFormatException e) {
            Alerts.showError("Error", "Invalid month");
            return;
        }

        int yearInt;
        try {
            yearInt = Integer.parseInt(year);
            if (yearInt < 2000 || yearInt > LocalDate.now().getYear() + 50) {
                Alerts.showError("Error", "Invalid year");
                return;
            }
        } catch (NumberFormatException e) {
            Alerts.showError("Error", "Invalid year");
            return;
        }

        IncomeCrud incomeCrud = new IncomeCrud();
        Map<String, Double> incomeByCategory = incomeCrud.getIncomeByCategory(monthInt, yearInt);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : incomeByCategory.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        incomePieChart.setData(pieChartData);

        ExpenseCrud expenseCrud = new ExpenseCrud();
        Map<String, Double> expenseByCategory = expenseCrud.getExpenseByCategory(monthInt, yearInt);

        pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : expenseByCategory.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        expensePieChart.setData(pieChartData);
    }

    /**
     * Handle the reset filter button
     */
    @FXML
    public void handleResetFilterButton() {
        monthComboBox.setValue(null);
        yearComboBox.setValue(null);
        populateIncomePieChart();
        populateExpensePieChart();
    }
}

