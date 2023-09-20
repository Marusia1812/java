module com.tracker.financetrackerapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.tracker.financetrackerapp to javafx.fxml;
    exports com.tracker.financetrackerapp;
}