package bd.edu.seu.tcms.user;

import bd.edu.seu.tcms.HelloApplication;
import bd.edu.seu.tcms.interfaces.SalesInterface;
import bd.edu.seu.tcms.model.CartItem;
import bd.edu.seu.tcms.services.SalesService;
import bd.edu.seu.tcms.utility.UserSession;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPayBill implements Initializable {
    @FXML
    private TextField addCouponField;

    @FXML
    private Label grandTotalField;

    @FXML
    private TableColumn<CartItem, String> nameColumn;

    @FXML
    private TableView<CartItem> payBillTableView;

    @FXML
    private TableColumn<CartItem, Number> priceColumn;

    @FXML
    private TableColumn<CartItem, Number> quantityColumn;

    @FXML
    private TableColumn<CartItem, Number> totalPriceColumn;

    @FXML
    private RadioButton paymentGroup;

    @FXML
    private ToggleGroup paymentRadio;

    private ObservableList<CartItem> cartToPurchase;

    @FXML
    void applyCouponEvent(ActionEvent event) {

    }

    @FXML
    void confirmPurchaseEvent(ActionEvent event) {
        if (cartToPurchase.isEmpty()) {
            showAlert("Error", "Your cart is empty.");
            return;
        }
        if (paymentRadio.getSelectedToggle() == null) {
            showAlert("Error", "Please select a payment method.");
            return;
        }

        String userName = UserSession.getLoggedInUserName();
        if (userName == null || userName.isEmpty()) {
            showAlert("Error", "User not logged in. Please log in again.");
            return;
        }

        RadioButton selectedRadioButton = (RadioButton) paymentRadio.getSelectedToggle();
        String paymentMethod = selectedRadioButton.getText();
        String couponCode = addCouponField.getText();

        SalesInterface salesService = new SalesService();
        boolean success = salesService.processSale(this.cartToPurchase, userName, paymentMethod, couponCode);

        if (success) {
            showAlert("Success", "Purchase successful!");
            this.cartToPurchase.clear();
            HelloApplication.changeScene("userDashboard");
        } else {
            showAlert("Error", "Purchase failed due to a database error.");
        }

    }

    private void showAlert(String title, String content) {
        Alert.AlertType type = title.equalsIgnoreCase("success") ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR;
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void deleteEvent(ActionEvent event) {CartItem selectedItem = payBillTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            this.cartToPurchase.remove(selectedItem);
            updateGrandTotal();
        }
    }



    @FXML
    void selectedRowEvent(MouseEvent event) {

    }



    @FXML
    void userBuyItemEvent(ActionEvent event) {
        HelloApplication.changeScene("userBuyItem");
        System.out.println("userBuyItem Called\n");
    }

    @FXML
    void userDashboardEvent(ActionEvent event) {
        HelloApplication.changeScene("userDashboard");
        System.out.println("User Dashboard Clicked\n");
    }

    @FXML
    void userInventoryEvent(ActionEvent event) {
        HelloApplication.changeScene("userInventory");
        System.out.println("User Inventory Called\n");
    }

    @FXML
    void userPayBillEvent(ActionEvent event) {
        HelloApplication.changeScene("userPayBill");
        System.out.println("User PayBill called\n");
    }

    @FXML
    void userSignOutEvent(ActionEvent event) {
        UserBuyItem.shoppingCart.clear();
        UserSession.logoutUser();
        HelloApplication.changeScene("welcomePage");
        System.out.println("You have been signed out\n");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cartToPurchase = UserBuyItem.shoppingCart;
        setupTable();
        payBillTableView.setItems(this.cartToPurchase);
        updateGrandTotal();
    }

    private void setupTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalPriceColumn.setCellValueFactory(cellData -> {
            CartItem item = cellData.getValue();
            double total = item.getQuantity() * item.getPrice();
            return new  SimpleDoubleProperty(total);
        });
    }

    private void updateGrandTotal() {
        double total = 0.0;
        for (CartItem item : this.cartToPurchase) {
            total += item.getQuantity() * item.getPrice();
        }
        grandTotalField.setText(String.format("%.2f TK", total));
    }
}
