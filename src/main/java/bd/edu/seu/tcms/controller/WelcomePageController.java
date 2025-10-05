package bd.edu.seu.tcms.controller;

import bd.edu.seu.tcms.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WelcomePageController {

    @FXML
    void AdminEvent(ActionEvent event) {
        HelloApplication.changeScene("adminLogin");
        System.out.println("Admin Called\n");
    }

    @FXML
    void UserEvent(ActionEvent event) {
        HelloApplication.changeScene("loginPage");
        System.out.println("User Called\n");
    }

    @FXML
    void registerEvent(ActionEvent event) {
        HelloApplication.changeScene("registrationPage");
        System.out.println("Register Called\n");
    }

    @FXML
    void facebookEvent(ActionEvent event) {

    }

}

