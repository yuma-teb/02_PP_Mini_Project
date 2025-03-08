# Project Guide: JDBC Database Setup and Mock Data Insertion

This guide walks you through setting up a database connection using JDBC, creating tables (`row` and `products`), and
inserting default values into the `row` table along with 10 mock products into the `products` table.

## Database Setup&#x20;

Ensure your database is accessible with the correct credentials:

- **Database Name**: `mydatabase`
- **Username**: `username`
- **Password**: `password`

Create a new database if it doesn’t exist:

```sql
CREATE
DATABASE miniproject;
```

## JDBC Connection&#x20;

Use the following Java code snippet to establish a JDBC connection:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/miniproject";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

## SQL Script for Table Creation&#x20;

Create the `row` and `products` tables:

```sql
CREATE TABLE products
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50),
    unitPrice   NUMERIC(10, 2),
    qty         INTEGER,
    importDate DATE
);

CREATE TABLE row
(
    id             SERIAL PRIMARY KEY,
    row_to_display INTEGER
);
```

## Insert Default Rows&#x20;

Insert default values into the `row` table:

```sql
INSERT INTO row (row_to_display)
VALUES (3);
```

## Mock Product Data&#x20;

Insert 10 mock products into the `products` table:

```sql
INSERT INTO products (name, unitPrice, qty, importDate)
VALUES ('Coca-Cola', 10.99, 5, '2024-03-01'),
       ('Pepsi', 12.49, 8, '2024-03-02'),
       ('Sprite', 15.99, 12, '2024-03-03'),
       ('Fanta', 8.99, 4, '2024-03-04'),
       ('Mountain Dew', 22.50, 7, '2024-03-05'),
       ('Dr Pepper', 5.49, 10, '2024-03-06'),
       ('Red Bull', 19.99, 6, '2024-03-07'),
       ('Monster Energy', 7.99, 9, '2024-03-08'),
       ('Gatorade', 14.75, 3, '2024-03-09'),
       ('Lipton Iced Tea', 9.99, 11, '2024-03-10');

```

After executing the script, your database will be set up with the necessary tables and data.

# Commit Message Guidelines

## Purpose

A well-structured commit message enhances project maintainability, improves collaboration, and simplifies debugging. This guide outlines best practices for writing effective commit messages.

## Types of Commits

Each commit should have a type that describes its purpose. Below are the most commonly used types:

- **feat**: Introduces a new feature or enhancement.
- **fix**: Resolves a bug or defect.
- **docs**: Updates documentation (e.g., fixing typos, improving clarity).
- **style**: Adjusts formatting, whitespace, or stylistic elements without altering functionality.
- **refactor**: Improves code structure or quality without changing behavior.
- **perf**: Enhances performance.
- **test**: Adds or updates tests.
- **build**: Modifies the build system, dependencies, or package configurations.
- **ci**: Updates Continuous Integration (CI) configurations or scripts.
- **chore**: General maintenance tasks that don’t fit other categories.
- **revert**: Undoes a previous commit.

## Scope (Optional)

The scope specifies the specific area of the project affected (e.g., a module or component). It appears in parentheses after the commit type. If no specific scope applies, it can be omitted.

### Examples:

- `feat(auth): Add login functionality`
- `fix(api): Correct user API endpoint`
- `docs(readme): Update installation instructions`

## Commit Message Structure

A commit message should be concise yet informative. Use the following format:

```
<type>(<scope>): <short summary>

<optional detailed explanation>
```

### Guidelines:
- **Use present tense**: "Add feature," not "Added feature."
- **Be concise**: Keep the summary under 72 characters.
- **Provide context if needed**: Use the body for additional details.
- **Reference issues**: Link related tickets when applicable.

### Examples:

- `feat(auth): Add JWT authentication support`
- `fix(ui): Fix button alignment in the header component`
- `perf(database): Optimize query performance`
- `revert(auth): Revert password hashing update`

By following these best practices, commit messages will remain clear, structured, and valuable for the entire development team.
