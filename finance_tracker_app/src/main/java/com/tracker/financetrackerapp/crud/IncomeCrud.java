package com.tracker.financetrackerapp.crud;

import com.tracker.financetrackerapp.IncomeEntry;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tracker.financetrackerapp.DatabaseManager.DB_URL;

/**
 * The income CRUD
 */
public class IncomeCrud {

    /**
     * Add a new income entry
     * @param amount The amount of the income
     * @param description The description of the income
     * @param category The category of the income
     * @param date The date of the income
     * @throws SQLException
     */
    public void addIncome(double amount, String description, String category, Date date) throws SQLException {
        String sql = "INSERT INTO Income (amount, description, category_id, date) VALUES (?, ?, (SELECT id FROM Category WHERE name = ?), ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, amount);
            pstmt.setString(2, description);
            pstmt.setString(3, category);
            pstmt.setDate(4, date);
            pstmt.executeUpdate();

            conn.commit();
        }
    }

    /**
     * Fetch all income entries
     * @return A list of all income entries
     */
    public List<IncomeEntry> getAllIncomeEntries() {
        List<IncomeEntry> entries = new ArrayList<>();
        String sql = "SELECT Income.id as id, amount, description, name as category, date FROM Income JOIN Category ON Income.category_id = Category.id";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                entries.add(new IncomeEntry(rs.getInt("id"), rs.getDouble("amount"), rs.getString("description"), rs.getString("category"), rs.getDate("date")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entries;
    }

    /**
     * Update an income entry
     * @param id The id of the income entry
     * @throws SQLException
     */
    public void deleteIncome(int id) throws SQLException {
        String sql = "DELETE FROM Income WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            conn.commit();
        }
    }

    /**
     * Fetch the total income by category
     * @return The total income
     */
    public Map<String, Double> getIncomeByCategory() {
        Map<String, Double> result = new HashMap<>();
        String sql = "SELECT c.name, SUM(i.amount) as total FROM Income i JOIN Category c ON i.category_id = c.id GROUP BY c.name";

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
     * Fetch the total income by category for a specific month and year
     * @param month The month
     * @param year The year
     * @return The total income by category for a specific month and year
     */
    public Map<String, Double> getIncomeByCategory(int month, int year) {
        Map<String, Double> result = new HashMap<>();
        String sql = "SELECT c.name, SUM(i.amount) as total " +
                "FROM Income i JOIN Category c ON i.category_id = c.id " +
                "WHERE EXTRACT(MONTH FROM i.date) = ? AND EXTRACT(YEAR FROM i.date) = ? " +
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
