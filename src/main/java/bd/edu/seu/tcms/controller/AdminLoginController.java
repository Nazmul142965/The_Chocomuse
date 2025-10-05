package bd.edu.seu.tcms.controller;

import bd.edu.seu.tcms.HelloApplication;
import bd.edu.seu.tcms.utility.ConnectionSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;

public class AdminLoginController {

    @FXML
    private TextField adminNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void loginEvent(ActionEvent event) {
        String name = adminNameField.getText();
        String password = passwordField.getText();
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
//            Statement statement = connection.createStatement();
            String query = "SELECT * FROM admin;";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet  = connection.createStatement().executeQuery(query);
            while(resultSet.next())
            {
                if(resultSet.getString("adminName").equals(name) && resultSet.getString("password").equals(password))
                {
                    HelloApplication.changeScene("adminDashboard");
                    System.out.println("Admin Successfully Logged In");
                }
                else if(adminNameField.getText().isEmpty() || passwordField.getText().isEmpty())
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Empty Fields");
                    alert.setContentText("Please fill in all fields");
                    alert.showAndWait();
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Credentials");
                    alert.setContentText("Please try again");
                    alert.showAndWait();
                }
            }
        }
        catch(SQLException e)
        {
            System.err.println("failed to connect to database");
            e.printStackTrace();
        }

    }

}
