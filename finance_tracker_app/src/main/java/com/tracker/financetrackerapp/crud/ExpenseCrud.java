package com.tracker.financetrackerapp.crud;

import com.tracker.financetrackerapp.ExpenseEntry;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tracker.financetrackerapp.DatabaseManager.DB_URL;

/**
 * The expense CRUD
 */
public class ExpenseCrud {
    /**
     * Add a new expense entry
     * @param amount The amount of the expense
     * @param description The description of the expense
     * @param category The category of the expense
     * @param date The date of the expense
     * @throws SQLException
     */
    public void addExpense(double amount, String description, String category, Date date) throws SQLException {
        String sql = "INSERT INTO Expense (amount, description, category_id, date) VALUES (?, ?, (SELECT id FROM Category WHERE name = ?), ?)";

        Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setDouble(1, amount);
        pstmt.setString(2, description);
        pstmt.setString(3, category);
        pstmt.setDate(4, date);
        pstmt.executeUpdate();

        conn.commit();
    }

    /**
     * Fetch all expense entries
     * @return A list of all expense entries
     */
    public List<ExpenseEntry> getAllExpenseEntries() {
        List<ExpenseEntry> entries = new ArrayList<>();
        String sql = "SELECT Expense.id as id, amount, description, name as category, date FROM Expense JOIN Category ON Expense.category_id = Category.id";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                entries.add(new ExpenseEntry(rs.getInt("id"), rs.getDouble("amount"), rs.getString("description"), rs.getString("category"), rs.getDate("date")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entries;
    }

    /**
     * Update an expense entry
     * @param id The id of the expense entry
     * @throws SQLException
     */
    public void deleteExpense(int id) throws SQLException {
        String sql = "DELETE FROM Expense WHERE id = ?";

        Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);
        pstmt.executeUpdate();

        conn.commit();
    }

    /**
     * Fetch all expenses by category
     * @return A map of all expenses by category
     */
    public Map<String, Double> getExpenseByCategory() {
        Map<String, Double> result = new HashMap<>();
        String sql = "SELECT c.name, SUM(e.amount) as total FROM Expense e JOIN Category c ON e.category_id = c.id GROUP BY c.name";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                result.put(rs.getString("name"), rs.getDouble("total"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Fetch all expenses by category for a specific month and year
     * @param month The month
     * @param year The year
     * @return A map of all expenses by category
     */
    public Map<String, Double> getExpenseByCategory(int month, int year) {
        Map<String, Double> result = new HashMap<>();
        String sql = "SELECT c.name, SUM(e.amount) as total " +
                "FROM Expense e JOIN Category c ON e.category_id = c.id " +
                "WHERE EXTRACT(MONTH FROM e.date) = ? AND EXTRACT(YEAR FROM e.date) = ? " +
                "GROUP BY c.name";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, month);
            pstmt.setInt(2, year);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.put(rs.getString("name"), rs.getDouble("total"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
