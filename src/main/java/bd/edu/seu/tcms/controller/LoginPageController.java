package bd.edu.seu.tcms.controller;

import bd.edu.seu.tcms.HelloApplication;
import bd.edu.seu.tcms.utility.ConnectionSingleton;
import bd.edu.seu.tcms.utility.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.*;

public class LoginPageController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userNameField;

    @FXML
    void forgetEvent(MouseEvent event) {

    }

    @FXML
    void loginEvent(ActionEvent event) {
        String name =  userNameField.getText();
        String password = passwordField.getText();

        if (name.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
            return;
        }

        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT * FROM user WHERE name = ? AND password = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                UserSession.loginUser(name);
                System.out.println("User '" + name + "' has successfully logged in.");

                HelloApplication.changeScene("userDashboard");
                System.out.println("Successfully inserted in user\n");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Credentials");
                alert.setContentText("Username or password is incorrect. Please try again.");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            System.err.println("Database error during login\n");
            e.printStackTrace();
        }

    }

    @FXML
    void regEvent(MouseEvent event) {
        HelloApplication.changeScene("registrationPage");
        System.out.println("Haven't registered called\n");

    }

}
