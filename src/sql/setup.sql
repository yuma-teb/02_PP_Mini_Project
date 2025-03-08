-- Create database if not exists (optional, should be run separately)
-- CREATE DATABASE mydatabase;

-- Switch to the database (only needed if running in an interactive session)
-- \c mydatabase

-- Create products table
CREATE TABLE products
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50),
    unitPrice   NUMERIC(10, 2),
    qty         INTEGER,
    import_date DATE
);

-- Create row table
CREATE TABLE row
(
    id             SERIAL PRIMARY KEY,
    row_to_display INTEGER
);

-- Insert default rows into the row table
INSERT INTO row (row_to_display)
VALUES (1),
       (2),
       (3);

-- Insert mock products
INSERT INTO products (name, unitPrice, qty, import_date)
VALUES ('Product 1', 10.99, 5, '2024-03-01'),
       ('Product 2', 12.49, 8, '2024-03-02'),
       ('Product 3', 15.99, 12, '2024-03-03'),
       ('Product 4', 8.99, 4, '2024-03-04'),
       ('Product 5', 22.50, 7, '2024-03-05'),
       ('Product 6', 5.49, 10, '2024-03-06'),
       ('Product 7', 19.99, 6, '2024-03-07'),
       ('Product 8', 7.99, 9, '2024-03-08'),
       ('Product 9', 14.75, 3, '2024-03-09'),
       ('Product 10', 9.99, 11, '2024-03-10');
