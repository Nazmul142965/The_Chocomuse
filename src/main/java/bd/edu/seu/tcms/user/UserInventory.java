package bd.edu.seu.tcms.user;

import bd.edu.seu.tcms.HelloApplication;
import bd.edu.seu.tcms.model.Sale;
import bd.edu.seu.tcms.model.SaleItem;
import bd.edu.seu.tcms.services.UserInventoryService;
import bd.edu.seu.tcms.utility.UserSession;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class UserInventory implements Initializable {
    @FXML
    private TableColumn<SaleItem, String> itemNameColumn;

    @FXML
    private TableColumn<SaleItem, Number> itemPriceColumn;

    @FXML
    private TableColumn<SaleItem, Number> itemQuantityColumn;

    @FXML
    private TableColumn<Sale, LocalDateTime> saleDateColumn;

    @FXML
    private TableColumn<Sale, Number> saleIdColumn;

    @FXML
    private TableView<SaleItem> saleItemsTableView;

    @FXML
    private TableColumn<Sale, Number> saleTotalColumn;

    @FXML
    private TableView<Sale> salesTableView;

    private UserInventoryService inventoryService = new UserInventoryService();

    @FXML
    void userBuyItemEvent(ActionEvent event) {
        HelloApplication.changeScene("userBuyItem");
        System.out.println("UserBuyItem Called\n");
    }

    @FXML
    void userDashboardEvent(ActionEvent event) {
        HelloApplication.changeScene("userDashboard");
        System.out.println("User Dashboard Called\n");
    }

    @FXML
    void userInventoryEvent(ActionEvent event) {
        HelloApplication.changeScene("userInventory");
        System.out.println("User inventory called\n");

    }

    @FXML
    void userPayBillEvent(ActionEvent event) {
        HelloApplication.changeScene("userPayBill");
        System.out.println("User pay bill Called\n");
    }

    @FXML
    void userSignOutEvent(ActionEvent event) {
        UserBuyItem.shoppingCart.clear();
        UserSession.logoutUser();
        HelloApplication.changeScene("welcomePage");
        System.out.println("User Signed Out");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupSalesTable();
        setupSaleItemsTable();

        loadUserSales();

        salesTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loadSaleItems(newSelection.getSaleId());
            }
        });
    }

    private void setupSalesTable() {
        saleIdColumn.setCellValueFactory(new PropertyValueFactory<>("saleId"));
        saleDateColumn.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        saleTotalColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
    }

    private void setupSaleItemsTable() {
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("chocolateName"));
        itemQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("priceAtSale"));
    }

    private void loadUserSales() {
        String currentUser = UserSession.getLoggedInUserName();
        if (currentUser != null && !currentUser.isEmpty()) {
            ObservableList<Sale> userSales = inventoryService.getSalesForUser(currentUser);
            salesTableView.setItems(userSales);
        }
    }

    private void loadSaleItems(int saleId) {
        ObservableList<SaleItem> items = inventoryService.getSaleItemsForSale(saleId);
        saleItemsTableView.setItems(items);
    }
}
