# Project Guide: JDBC Database Setup and Mock Data Insertion

This guide walks you through setting up a database connection using JDBC, creating tables (`row` and `products`), and inserting default values into the `row` table along with 10 mock products into the `products` table.

## Database Setup&#x20;

Ensure your database is accessible with the correct credentials:

- **Database Name**: `mydatabase`
- **Username**: `username`
- **Password**: `password`

Create a new database if it doesn’t exist:

```sql
CREATE DATABASE miniproject;
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
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    unitPrice NUMERIC(10, 2),
    qty INTEGER,
    import_date DATE
);

CREATE TABLE row (
    id SERIAL PRIMARY KEY,
    row_to_display INTEGER
);
```

## Insert Default Rows&#x20;

Insert default values into the `row` table:

```sql
INSERT INTO row (row_to_display) VALUES (1), (2), (3);
```

## Mock Product Data&#x20;

Insert 10 mock products into the `products` table:

```sql
INSERT INTO products (name, unitPrice, qty, import_date) VALUES
('Product 1', 10.99, 5, '2024-03-01'),
('Product 2', 12.49, 8, '2024-03-02'),
('Product 3', 15.99, 12, '2024-03-03'),
('Product 4', 8.99, 4, '2024-03-04'),
('Product 5', 22.50, 7, '2024-03-05'),
('Product 6', 5.49, 10, '2024-03-06'),
('Product 7', 19.99, 6, '2024-03-07'),
('Product 8', 7.99, 9, '2024-03-08'),
('Product 9', 14.75, 3, '2024-03-09'),
('Product 10', 9.99, 11, '2024-03-10');
```

## Execute SQL Script Manually in PostgreSQL&#x20;

To execute the SQL script and create tables with mock data in PostgreSQL, follow these steps:

1. Save the SQL script in a file, e.g., `setup.sql`.
2. Open a terminal and connect to your PostgreSQL database:

```sh
psql -U username -d mydatabase
```

3. Run the script inside the PostgreSQL session:

```sh
\i setup.sql
```

Alternatively, you can run the script directly from the terminal:

```sh
psql -U username -d mydatabase -f setup.sql
```

After executing the script, your database will be set up with the necessary tables and data.



# Query Builder Usage Guide

This guide explains how to use the `InsertQueryBuilder`, `SelectQueryBuilder`, and `UpdateQueryBuilder` classes for constructing SQL queries dynamically.

## 1. InsertQueryBuilder
Used to construct `INSERT` queries.

### Example:
```java
InsertQueryBuilder insert = new InsertQueryBuilder(TableName.PRODUCT)
    .setValue("name", "Laptop")
    .setValue("price", 1200);

String query = insert.buildQuery();
System.out.println(query); // Output: INSERT INTO Product (name, price) VALUES (?, ?)
```

---

## 2. SelectQueryBuilder
Used to construct `SELECT` queries.

### Example:
```java
SelectQueryBuilder select = new SelectQueryBuilder(TableName.PRODUCT)
    .where("price", 1200);

String query = select.buildQuery();
System.out.println(query); // Output: SELECT * FROM Product WHERE price = ?
```

Using custom operators:
```java
SelectQueryBuilder select = new SelectQueryBuilder(TableName.PRODUCT)
    .where("price", 1000, QueryOperator.GREATER_THAN);

String query = select.buildQuery();
System.out.println(query); // Output: SELECT * FROM Product WHERE price > ?
```

Selecting specific columns:
```java
SelectQueryBuilder select = new SelectQueryBuilder(TableName.PRODUCT)
    .select("name", "price")
    .where("id", 1);

String query = select.buildQuery();
System.out.println(query); // Output: SELECT name, price FROM Product WHERE id = ?
```

---

## 3. UpdateQueryBuilder
Used to construct `UPDATE` queries.

### Example:
```java
UpdateQueryBuilder update = new UpdateQueryBuilder(TableName.PRODUCT)
    .setValue("name", "Gaming Laptop")
    .where("id", 1);

String query = update.buildQuery();
System.out.println(query); // Output: UPDATE Product SET name = ? WHERE id = ?
```

Updating multiple fields:
```java
UpdateQueryBuilder update = new UpdateQueryBuilder(TableName.PRODUCT)
    .setValue("name", "Gaming Laptop")
    .setValue("price", 1500)
    .where("id", 1);

String query = update.buildQuery();
System.out.println(query); // Output: UPDATE Product SET name = ?, price = ? WHERE id = ?
```

---

## Notes:
- `?` placeholders are used for parameterized queries to prevent SQL injection.
- The `parameters` list in each builder stores the actual values to be used with prepared statements.
- The `where` method is used to filter records in `SELECT` and `UPDATE` queries.
- The `setValue` method is used to specify column values for `INSERT` and `UPDATE` queries.

This guide provides a quick reference for utilizing the query builders effectively.

### Types of Commits

Each commit should have a type that describes the change. Below are the most commonly used commit types:

- **feat**: A new feature or enhancement.
- **fix**: A bug fix.
- **docs**: Documentation changes (e.g., fixing typos or adding comments).
- **style**: Code formatting changes (e.g., white-space, semicolons, etc.) that don't affect the logic.
- **refactor**: A change that doesn't fix a bug or add a feature but improves code quality.
- **perf**: A performance improvement.
- **test**: Adding or modifying tests.
- **build**: Changes to the build process or dependencies.
- **ci**: Continuous Integration related changes (e.g., CI configuration).
- **chore**: Routine tasks or changes that don’t fit into any other category.
- **revert**: Reverts a previous commit.

### Scope (Optional)

The scope is a small part of the project that the commit affects (e.g., a specific module or feature). It is enclosed in parentheses and placed right after the type. If there's no specific scope, it can be omitted.

Example:
- **feat(auth)**: Add login functionality
- **fix(api)**: Corrected the user API endpoint
- **docs(readme)**: Update installation instructions

### Message

The message should be clear, concise, and written in the present tense. It should briefly describe the change without going into excessive detail. 

Example:
- **feat(auth):** Add JWT authentication support
- **fix(ui):** Fix button alignment in header component


