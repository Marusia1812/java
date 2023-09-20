package com.tracker.financetrackerapp.crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.tracker.financetrackerapp.DatabaseManager.DB_URL;

/**
 * The category CRUD
 */
public class CategoryCrud {
    /**
     * Add a new category
     * @param categoryName The name of the category
     * @throws SQLException
     */
    public void addCategory(String categoryName) throws SQLException {
        String sql = "INSERT INTO Category (name) VALUES (?)";

        Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, categoryName);
        pstmt.executeUpdate();

        conn.commit();
    }

    /**
     * Fetch all categories
     * @return A list of all categories
     */
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT name FROM Category";

        try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                categories.add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    /**
     * Update a category
     * @param oldName The old name of the category
     * @param newName The new name of the category
     * @throws SQLException
     */
    public void updateCategory(String oldName, String newName) throws SQLException {
        String sql = "UPDATE Category SET name = ? WHERE name = ?";

        Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, newName);
        pstmt.setString(2, oldName);
        pstmt.executeUpdate();

        conn.commit();
    }

    /**
     * Delete a category
     * @param categoryName The name of the category
     * @throws SQLException
     */
    public void deleteCategory(String categoryName) throws SQLException {
        String sql = "DELETE FROM Category WHERE name = ?";

        Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, categoryName);
        pstmt.executeUpdate();

        conn.commit();
    }

    /**
     * Check if a category exists
     * @param categoryName The name of the category
     * @return true if the category exists, false otherwise
     */
    public boolean categoryExists(String categoryName) {
        String sql = "SELECT COUNT(*) FROM Category WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, categoryName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
