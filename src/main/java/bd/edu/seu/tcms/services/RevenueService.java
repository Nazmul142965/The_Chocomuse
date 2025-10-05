package bd.edu.seu.tcms.services;

import bd.edu.seu.tcms.utility.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RevenueService {

    private double getSingleValueFromQuery(String query) {
        double value = 0.0;
        try {
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                value = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get sales for user\n");
            e.printStackTrace();
        }
        return value;
    }

    public double getTotalRevenue() {
        return getSingleValueFromQuery("SELECT SUM(total_amount) FROM sales");
    }

    public int getTotalItemsSold() {
        return (int) getSingleValueFromQuery("SELECT SUM(quantity) FROM sale_items");
    }

    public double getAverageOrderValue() {
        return getSingleValueFromQuery("SELECT AVG(total_amount) FROM sales");
    }

    public double getTotalInventoryValue() {
        return getSingleValueFromQuery("SELECT SUM(quantity * price) FROM chocolate");
    }

    public double getTotalAmountSpent(String userName) {
        double totalSpent = 0.0;
        String query = "SELECT SUM(total_amount) FROM sales WHERE user_name = ?";

        try {
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                totalSpent = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalSpent;
    }
}