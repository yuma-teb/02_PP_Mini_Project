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

