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


