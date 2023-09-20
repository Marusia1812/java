package com.tracker.financetrackerapp;

import java.util.Date;

/**
 * The expense entry
 */
public class ExpenseEntry {
    private int id;
    private double amount;
    private String description;
    private String category;
    private Date date;

    /**
     * The expense entry
     * @param id The id of the expense entry
     * @param amount The amount of the expense entry
     * @param description The description of the expense entry
     * @param category The category of the expense entry
     */
    public ExpenseEntry(int id, double amount, String description, String category, Date date) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    // Getters and Setters

    /**
     * Get the id of the expense entry
     * @return The id of the expense entry
     */
    public int getId() {
        return id;
    }

    /**
     * Get the amount of the expense entry
     * @return The amount of the expense entry
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Set the amount of the expense entry
     * @param amount The amount of the expense entry
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Get the description of the expense entry
     * @return The description of the expense entry
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the expense entry
     * @param description The description of the expense entry
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the category of the expense entry
     * @return The category of the expense entry
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the category of the expense entry
     * @param category The category of the expense entry
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Get the date of the expense entry
     * @return The date of the expense entry
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the date of the expense entry
     * @param date The date of the expense entry
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the string representation of the expense entry
     * @return The string representation of the expense entry
     */
    @Override
    public String toString() {
        return description + ": " + amount + " -> " + category + " (" + date + ")";
    }
}

