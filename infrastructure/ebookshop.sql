CREATE DATABASE IF NOT EXISTS ebookshop;
USE ebookshop;

--Users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255)
);

--Books table
CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50),
    price FLOAT,
    qty INT,
    img VARCHAR(255)
);

--Orders table
CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    address VARCHAR(255),
    total_price DECIMAL(10,2),
    order_date DATETIME,
    status VARCHAR(20)
);

--Order Items table
CREATE TABLE IF NOT EXISTS order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    book_id INT,
    quantity INT,
    price DECIMAL(10,2),
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE RESTRICT
);

--dummy data fields
INSERT IGNORE INTO books (id, title, author, price, qty, img) VALUES
(1, 'Java for dummies', 'Tan Ah Teck', 11.11, 11, '1.jpg'),
(2, 'More Java for dummies', 'Tan Ah Teck', 22.22, 22, '2.jpg'),
(3, 'More Java for more dummies', 'Mohammad Ali', 33.33, 33, '3.jpg'),
(4, 'A Cup of Java', 44.44, 44, '4.jpg'),
(4, 'A Teaspoon of Java', 55.55, 55, '5.jpg');
