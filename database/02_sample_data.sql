-- =========================================
-- The Chocomuse Sample Data Script
-- =========================================
-- This script inserts sample data for testing the application
-- Run this script after creating the database structure

USE chocomuse;

-- =========================================
-- INSERT ADMIN DATA
-- =========================================
-- Default admin credentials
INSERT INTO admin (adminName, password) VALUES 
('admin', 'admin123'),
('manager', 'manager123');

-- =========================================
-- INSERT SAMPLE USERS
-- =========================================
-- Sample user accounts for testing
INSERT INTO user (name, email, password) VALUES 
('john_doe', 'john.doe@email.com', 'password123'),
('jane_smith', 'jane.smith@email.com', 'password456'),
('mike_wilson', 'mike.wilson@email.com', 'password789'),
('sarah_jones', 'sarah.jones@email.com', 'password321');

-- =========================================
-- INSERT SAMPLE CHOCOLATE PRODUCTS
-- =========================================
-- Sample chocolate inventory
INSERT INTO chocolate (id, name, weight, quantity, price, produceDate, expiryDate, imagePath) VALUES 
(1, 'Dark Chocolate Premium', 100.0, 50, 12.99, '2024-01-15', '2025-01-15', '/images/dark_chocolate_premium.jpg'),
(2, 'Milk Chocolate Classic', 80.0, 75, 8.99, '2024-02-01', '2025-02-01', '/images/milk_chocolate_classic.jpg'),
(3, 'White Chocolate Delight', 90.0, 30, 15.99, '2024-01-20', '2024-12-20', '/images/white_chocolate_delight.jpg'),
(4, 'Hazelnut Crunch', 120.0, 40, 18.99, '2024-02-10', '2025-02-10', '/images/hazelnut_crunch.jpg'),
(5, 'Almond Joy Bar', 75.0, 60, 10.99, '2024-01-25', '2025-01-25', '/images/almond_joy_bar.jpg'),
(6, 'Caramel Fusion', 110.0, 25, 22.99, '2024-02-05', '2025-02-05', '/images/caramel_fusion.jpg'),
(7, 'Mint Chocolate Chip', 95.0, 35, 14.99, '2024-01-30', '2024-12-30', '/images/mint_chocolate_chip.jpg'),
(8, 'Orange Zest Dark', 85.0, 45, 16.99, '2024-02-15', '2025-02-15', '/images/orange_zest_dark.jpg'),
(9, 'Coconut Paradise', 100.0, 20, 19.99, '2024-01-10', '2024-12-10', '/images/coconut_paradise.jpg'),
(10, 'Strawberry Cream', 70.0, 55, 13.99, '2024-02-20', '2025-02-20', '/images/strawberry_cream.jpg');

-- =========================================
-- INSERT SAMPLE SALES DATA
-- =========================================
-- Sample sales transactions
INSERT INTO sales (user_name, sale_date, total_amount, status, payment_method, coupon_used) VALUES 
('john_doe', '2024-03-01 10:30:00', 25.98, 'Paid', 'Credit Card', NULL),
('jane_smith', '2024-03-02 14:15:00', 45.97, 'Paid', 'Cash', 'SAVE10'),
('mike_wilson', '2024-03-03 09:45:00', 18.99, 'Paid', 'Debit Card', NULL),
('sarah_jones', '2024-03-04 16:20:00', 38.98, 'Paid', 'Credit Card', NULL),
('john_doe', '2024-03-05 11:10:00', 52.97, 'Paid', 'Cash', 'WELCOME20');

-- =========================================
-- INSERT SAMPLE SALE ITEMS
-- =========================================
-- Items for each sale transaction
-- Sale 1: john_doe bought 2 Dark Chocolate Premium
INSERT INTO sale_items (sale_id, chocolate_id, quantity, price_at_sale) VALUES 
(1, 1, 2, 12.99);

-- Sale 2: jane_smith bought multiple items
INSERT INTO sale_items (sale_id, chocolate_id, quantity, price_at_sale) VALUES 
(2, 2, 2, 8.99),
(2, 5, 1, 10.99),
(2, 7, 1, 14.99);

-- Sale 3: mike_wilson bought 1 Hazelnut Crunch
INSERT INTO sale_items (sale_id, chocolate_id, quantity, price_at_sale) VALUES 
(3, 4, 1, 18.99);

-- Sale 4: sarah_jones bought 2 different items
INSERT INTO sale_items (sale_id, chocolate_id, quantity, price_at_sale) VALUES 
(4, 6, 1, 22.99),
(4, 8, 1, 16.99);

-- Sale 5: john_doe made another purchase
INSERT INTO sale_items (sale_id, chocolate_id, quantity, price_at_sale) VALUES 
(5, 3, 1, 15.99),
(5, 9, 1, 19.99),
(5, 10, 1, 13.99);

-- =========================================
-- UPDATE CHOCOLATE QUANTITIES
-- =========================================
-- Update quantities based on sales (simulating real inventory reduction)
UPDATE chocolate SET quantity = quantity - 2 WHERE id = 1; -- Dark Chocolate Premium
UPDATE chocolate SET quantity = quantity - 2 WHERE id = 2; -- Milk Chocolate Classic
UPDATE chocolate SET quantity = quantity - 1 WHERE id = 3; -- White Chocolate Delight
UPDATE chocolate SET quantity = quantity - 1 WHERE id = 4; -- Hazelnut Crunch
UPDATE chocolate SET quantity = quantity - 1 WHERE id = 5; -- Almond Joy Bar
UPDATE chocolate SET quantity = quantity - 1 WHERE id = 6; -- Caramel Fusion
UPDATE chocolate SET quantity = quantity - 1 WHERE id = 7; -- Mint Chocolate Chip
UPDATE chocolate SET quantity = quantity - 1 WHERE id = 8; -- Orange Zest Dark
UPDATE chocolate SET quantity = quantity - 1 WHERE id = 9; -- Coconut Paradise
UPDATE chocolate SET quantity = quantity - 1 WHERE id = 10; -- Strawberry Cream

COMMIT;

-- =========================================
-- Sample data inserted successfully
-- =========================================
-- You can now login with:
-- Admin: username='admin', password='admin123'
-- User: username='john_doe', password='password123'
-- =========================================