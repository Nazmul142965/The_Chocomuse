package bd.edu.seu.tcms.admin;

import bd.edu.seu.tcms.HelloApplication;
import bd.edu.seu.tcms.services.ChocolateService;
import bd.edu.seu.tcms.services.RevenueService;
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

public class AdminRevenue implements Initializable {

    @FXML
    private Label averageOrderValueField;

    @FXML
    private Label totalItemSoldField;

    @FXML
    private Label totalProductField;

    @FXML
    private Label totalRevenueField;

    @FXML
    void addItemEvent(ActionEvent event) {
        HelloApplication.changeScene("adminAddItem");
        System.out.println("adminAddItem Called\n");

    }

    @FXML
    void dashboardEvent(ActionEvent event) {
        HelloApplication.changeScene("adminDashboard");
        System.out.println("adminDashboard Called\n");

    }

    @FXML
    void inventoryEvent(ActionEvent event) {
        HelloApplication.changeScene("adminInventory");
        System.out.println("adminInventory Called\n");

    }

    @FXML
    void revenueEvent(ActionEvent event) {
        HelloApplication.changeScene("adminRevenue");
        System.out.println("adminRevenue Called\n");

    }

    @FXML
    void signOutEvent(ActionEvent event) {
        HelloApplication.changeScene("WelcomePage");
        System.out.println("WelcomePage  Called\n");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDashboardStats();
    }
    private void loadDashboardStats() {
        RevenueService revenueService = new RevenueService();

        // 1. Total Revenue
        double totalRevenue = revenueService.getTotalRevenue();
        totalRevenueField.setText(String.format("%.2f TK", totalRevenue));

        // 2. Total Product by Amount (Total Inventory Value)
        double totalInventoryValue = revenueService.getTotalInventoryValue();
        totalProductField.setText(String.format("%.2f TK", totalInventoryValue));

        // 3. Total Item Sold
        int totalItemsSold = revenueService.getTotalItemsSold();
        totalItemSoldField.setText(String.valueOf(totalItemsSold));

        // 4. Average Order Value
        double avgOrderValue = revenueService.getAverageOrderValue();
        averageOrderValueField.setText(String.format("%.2f TK", avgOrderValue));


    }


}

