-- =========================================
-- The Chocomuse Database Creation Script
-- =========================================
-- This script creates the complete database structure for The Chocomuse Management System
-- Run this script first to set up the database

-- Create database
CREATE DATABASE IF NOT EXISTS chocomuse;
USE chocomuse;

-- =========================================
-- TABLE: admin
-- =========================================
-- Stores admin login credentials
CREATE TABLE IF NOT EXISTS admin (
    adminName VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (adminName)
);

-- =========================================
-- TABLE: user
-- =========================================
-- Stores user registration and login information
CREATE TABLE IF NOT EXISTS user (
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (name),
    UNIQUE KEY unique_email (email)
);

-- =========================================
-- TABLE: chocolate
-- =========================================
-- Stores chocolate product information
CREATE TABLE IF NOT EXISTS chocolate (
    id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    weight DOUBLE NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    produceDate VARCHAR(20) NOT NULL,
    expiryDate VARCHAR(20) NOT NULL,
    imagePath VARCHAR(255),
    PRIMARY KEY (id)
);

-- =========================================
-- TABLE: sales
-- =========================================
-- Stores sales transaction information
CREATE TABLE IF NOT EXISTS sales (
    sale_id INT AUTO_INCREMENT,
    user_name VARCHAR(50) NOT NULL,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DOUBLE NOT NULL,
    status VARCHAR(20) DEFAULT 'Paid',
    payment_method VARCHAR(50),
    coupon_used VARCHAR(50),
    PRIMARY KEY (sale_id),
    FOREIGN KEY (user_name) REFERENCES user(name) ON DELETE CASCADE
);

-- =========================================
-- TABLE: sale_items
-- =========================================
-- Stores individual items in each sale
CREATE TABLE IF NOT EXISTS sale_items (
    sale_item_id INT AUTO_INCREMENT,
    sale_id INT NOT NULL,
    chocolate_id INT NOT NULL,
    quantity INT NOT NULL,
    price_at_sale DOUBLE NOT NULL,
    PRIMARY KEY (sale_item_id),
    FOREIGN KEY (sale_id) REFERENCES sales(sale_id) ON DELETE CASCADE,
    FOREIGN KEY (chocolate_id) REFERENCES chocolate(id) ON DELETE CASCADE
);

-- =========================================
-- Add indexes for better performance
-- =========================================
CREATE INDEX idx_sales_user_name ON sales(user_name);
CREATE INDEX idx_sales_sale_date ON sales(sale_date);
CREATE INDEX idx_sale_items_sale_id ON sale_items(sale_id);
CREATE INDEX idx_sale_items_chocolate_id ON sale_items(chocolate_id);

COMMIT;

-- =========================================
-- Database structure created successfully
-- =========================================