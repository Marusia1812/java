# Personal Finance Tracker

A JavaFX application designed to help users manage and visualize their personal finances. The application provides functionalities to track income and expenses, categorize them, and visualize the financial data using pie charts.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Income & Expense Tracking**: Add, view, and delete income and expense entries.
- **Category Management**: Add, edit, or delete categories for income and expenses.
- **Visualization**: Visualize income and expenses by category using pie charts.
- **Settings**: Manage categories for better organization of entries.

## Architecture

### Main Components:

- **MainApplication.java**: The entry point of the application. Initializes the database and loads the main UI.
- **DatabaseManager.java**: Manages database initialization and table creation.
- **AddEntryModalController.java**: Handles the addition of both income and expense entries.
- **ExpenseController.java & IncomeController.java**: Controllers for managing income and expense entries.
- **VisualizationController.java**: Populates the pie charts with data from the database.
- **SettingsController.java**: Allows users to manage (add, edit, delete) categories.

### Utility Classes:

- **Alerts.java**: Utility class for displaying alert messages to the user.
- **EntryType.java**: Enum representing the type of entry (Income or Expense).

### Data Models:

- **ExpenseEntry.java & IncomeEntry.java**: Data models representing expense and income entries.

### CRUD Operations:

- **CategoryCrud.java**: CRUD operations for categories.
- **ExpenseCrud.java**: CRUD operations for expenses.
- **IncomeCrud.java**: CRUD operations for incomes.

# Commands

## Documentation

```shell
mvn site
```

## Run

```shell
mvn clean javafx:run
```
