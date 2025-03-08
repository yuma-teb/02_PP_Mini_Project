-- Create database if not exists (optional, should be run separately)
-- CREATE DATABASE mydatabase;

-- Switch to the database (only needed if running in an interactive session)
-- \c mydatabase

-- drop tables first
drop table if exists products;
drop table if exists row;

-- Create products table
CREATE TABLE products
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(50),
    unitPrice  NUMERIC(10, 2),
    qty        INTEGER,
    importDate DATE
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
