package bd.edu.seu.tcms.admin;

import bd.edu.seu.tcms.HelloApplication;
import bd.edu.seu.tcms.utility.ConnectionSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminDashboard implements Initializable {

    @FXML
    private Label currentSellLabel;

    @FXML
    void addItemEvent(ActionEvent event) {
        HelloApplication.changeScene("adminAddItem");
        System.out.println("Admin Add Item Called\n");

    }

    @FXML
    void dashboardEvent(ActionEvent event) {
        HelloApplication.changeScene("adminDashboard");
        System.out.println("Admin Dashboard Called\n");
    }

    @FXML
    void inventoryEvent(ActionEvent event) {
        HelloApplication.changeScene("adminInventory");
        System.out.println("Admin Inventory Called/n");
    }

    @FXML
    void revenueEvent(ActionEvent event) {
        HelloApplication.changeScene("adminRevenue");
        System.out.println("Admin Revenue  Called/n");
    }

    @FXML
    void signOutEvent(ActionEvent event) {
        HelloApplication.changeScene("welcomePage");
        System.out.println("Sinning out, bye/n");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT total_amount FROM sales ORDER BY sale_id DESC LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                double lastSaleAmount = rs.getDouble("total_amount");
                currentSellLabel.setText(String.format(" %.2f TK", lastSaleAmount));
            } else {
                currentSellLabel.setText("Current Sold: 0.00 TK");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get current sales");
            e.printStackTrace();
            currentSellLabel.setText("Current Sold: Error");
        }
    }
}
