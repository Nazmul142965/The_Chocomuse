package bd.edu.seu.tcms.user;

import bd.edu.seu.tcms.HelloApplication;
import bd.edu.seu.tcms.utility.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDashboard implements Initializable {

    @FXML
    private Label currentUserName;

    @FXML
    private Label highestBoughtProductField;

    @FXML
    private Label totalAmountSpentField;

    @FXML
    void userBuyItemEvent(ActionEvent event) {
        HelloApplication.changeScene("UserBuyItem");
        System.out.println("USER BUY ITEM  Called\n");

    }

    @FXML
    void userDashboardEvent(ActionEvent event) {
        HelloApplication.changeScene("UserDashboard");
        System.out.println("USER DASHBOARD  Called\n");

    }

    @FXML
    void userInventoryEvent(ActionEvent event) {
        HelloApplication.changeScene("UserInventory");
        System.out.println("USER INVENTORY  Called\n");

    }

    @FXML
    void userPayBillEvent(ActionEvent event) {
        HelloApplication.changeScene("UserPayBill");
        System.out.println("USER PAY BILL  Called\n");
    }

    @FXML
    void userSignOutEvent(ActionEvent event) {
        UserBuyItem.shoppingCart.clear();
        UserSession.logoutUser();
        HelloApplication.changeScene("WelcomePage");
        System.out.println("USER SING OUT  Called\n");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String userName = UserSession.getLoggedInUserName();
        currentUserName.setText(userName + "!");
    }
}
