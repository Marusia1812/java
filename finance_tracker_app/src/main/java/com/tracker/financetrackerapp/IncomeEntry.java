package com.tracker.financetrackerapp;

import java.util.Date;

/**
 * The income entry
 */
public class IncomeEntry {
    private int id;
    private double amount;
    private String description;
    private String category;
    private Date date;

    /**
     * The income entry
     * @param id The id of the income entry
     * @param amount The amount of the income entry
     * @param description The description of the income entry
     * @param category The category of the income entry
     * @param date The date of the income entry
     */
    public IncomeEntry(int id, double amount, String description, String category, Date date) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    // Getters and Setters

    /**
     * Get the id of the income entry
     * @return The id of the income entry
     */
    public int getId() {
        return id;
    }

    /**
     * get the amount of the income entry
     * @return The amount of the income entry
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Set the amount of the income entry
     * @param amount The amount of the income entry
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Get the description of the income entry
     * @return The description of the income entry
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the income entry
     * @param description The description of the income entry
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the category of the income entry
     * @return The category of the income entry
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the category of the income entry
     * @param category The category of the income entry
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Get the date of the income entry
     * @return The date of the income entry
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the date of the income entry
     * @param date The date of the income entry
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the string representation of the income entry
     * @return The string representation of the income entry
     */
    @Override
    public String toString() {
        return description + ": " + amount + " -> " + category + " (" + date + ")";
    }
}
