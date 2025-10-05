package bd.edu.seu.tcms.services;

import bd.edu.seu.tcms.interfaces.SalesInterface;
import bd.edu.seu.tcms.model.CartItem;
import bd.edu.seu.tcms.utility.ConnectionSingleton;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public class SalesService implements SalesInterface {

    @Override
    public boolean processSale(ObservableList<CartItem> cartItems, String userName, String paymentMethod, String couponCode) {
        Connection connection = null;
        try {
            connection = ConnectionSingleton.getConnection();
            connection.setAutoCommit(false);

            String salesQuery = "INSERT INTO sales (user_name, sale_date, total_amount, status, payment_method, coupon_used) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement salesStmt = connection.prepareStatement(salesQuery, Statement.RETURN_GENERATED_KEYS);

            // Calculate total amount
            double totalAmount = 0;
            for (CartItem item : cartItems) {
                totalAmount += item.getQuantity() * item.getPrice();
            }

            salesStmt.setString(1, userName);
            salesStmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            salesStmt.setDouble(3, totalAmount);
            salesStmt.setString(4, "Paid");
            salesStmt.setString(5, paymentMethod);

            if (couponCode != null && !couponCode.trim().isEmpty()) {
                salesStmt.setString(6, couponCode.trim());
            } else {
                salesStmt.setNull(6, Types.VARCHAR);
            }
            salesStmt.executeUpdate();

            ResultSet generatedKeys = salesStmt.getGeneratedKeys();
            int saleId;
            if (generatedKeys.next()) {
                saleId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating sale failed, no ID obtained.");
            }

            for (CartItem item : cartItems) {
                String saleItemsQuery = "INSERT INTO sale_items (sale_id, chocolate_id, quantity, price_at_sale) VALUES (?, ?, ?, ?)";
                PreparedStatement saleItemsStmt = connection.prepareStatement(saleItemsQuery);
                saleItemsStmt.setInt(1, saleId);
                saleItemsStmt.setInt(2, item.getId()); // Using getId()
                saleItemsStmt.setInt(3, item.getQuantity());
                saleItemsStmt.setDouble(4, item.getPrice()); // Using getPrice()
                saleItemsStmt.executeUpdate();

                String updateStockQuery = "UPDATE chocolate SET quantity = quantity - ? WHERE id = ?";
                PreparedStatement updateStockStmt = connection.prepareStatement(updateStockQuery);
                updateStockStmt.setInt(1, item.getQuantity());
                updateStockStmt.setInt(2, item.getId()); // Using getId()
                updateStockStmt.executeUpdate();
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}