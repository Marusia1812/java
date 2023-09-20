package com.tracker.financetrackerapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * The database manager
 */
public class DatabaseManager {
    /**
     * The database URL
     */
    public static final String DB_URL = "jdbc:h2:~/financeTracker";

    /**
     * Initialize the database
     */
    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Create Category table
            String createCategoryTable = "CREATE TABLE IF NOT EXISTS Category (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL UNIQUE" +
                    ")";
            stmt.execute(createCategoryTable);

            // Create Income table
            String createIncomeTable = "CREATE TABLE IF NOT EXISTS Income (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "amount DOUBLE NOT NULL," +
                    "description VARCHAR(255)," +
                    "date DATE NOT NULL DEFAULT CURRENT_DATE," +
                    "category_id INT," +
                    "FOREIGN KEY (category_id) REFERENCES Category(id)" +
                    ")";
            stmt.execute(createIncomeTable);

            // Create Expense table
            String createExpenseTable = "CREATE TABLE IF NOT EXISTS Expense (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "amount DOUBLE NOT NULL," +
                    "description VARCHAR(255)," +
                    "date DATE NOT NULL DEFAULT CURRENT_DATE," +
                    "category_id INT," +
                    "FOREIGN KEY (category_id) REFERENCES Category(id)" +
                    ")";
            stmt.execute(createExpenseTable);

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}