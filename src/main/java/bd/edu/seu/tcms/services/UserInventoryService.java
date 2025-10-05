package bd.edu.seu.tcms.services;

import bd.edu.seu.tcms.interfaces.UserInventoryInterface;
import bd.edu.seu.tcms.model.Sale;
import bd.edu.seu.tcms.model.SaleItem;
import bd.edu.seu.tcms.utility.ConnectionSingleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInventoryService implements UserInventoryInterface {

    @Override
    public ObservableList<Sale> getSalesForUser(String userName) {
        ObservableList<Sale> salesList = FXCollections.observableArrayList();
        String query = "SELECT * FROM sales WHERE user_name = ? ORDER BY sale_date DESC";

        try {
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                salesList.add(new Sale(
                        rs.getInt("sale_id"),
                        rs.getString("user_name"),
                        rs.getTimestamp("sale_date").toLocalDateTime(),
                        rs.getDouble("total_amount")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get sales for user\n");
            e.printStackTrace();
        }
        return salesList;
    }

    @Override
    public ObservableList<SaleItem> getSaleItemsForSale(int saleId) {
        ObservableList<SaleItem> itemsList = FXCollections.observableArrayList();

        String query = "SELECT c.name, si.quantity, si.price_at_sale " +
                "FROM sale_items si " +
                "JOIN chocolate c ON si.chocolate_id = c.id " +
                "WHERE si.sale_id = ?";

        try {
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, saleId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                itemsList.add(new SaleItem(
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price_at_sale")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get sales for sale item\n");
            e.printStackTrace();
        }
        return itemsList;
    }
}