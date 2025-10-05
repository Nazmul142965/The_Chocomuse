package bd.edu.seu.tcms.controller;

import bd.edu.seu.tcms.HelloApplication;
import bd.edu.seu.tcms.utility.ConnectionSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationPageController {

    @FXML
    private TextField regiEmail;

    @FXML
    private TextField regiName;

    @FXML
    private PasswordField regiPassword;

    @FXML
    void alreadyRegEvent(MouseEvent event) {
        HelloApplication.changeScene("loginPage");
        System.out.println("Login page Called");
    }

    @FXML
    void registrationEvent(ActionEvent event) {
        String email = regiEmail.getText();
        String name = regiName.getText();
        String password = regiPassword.getText();
        //varcher 50, varchar 50, varchar 10


        try{
            Connection connection = ConnectionSingleton.getConnection();
            String query = "INSERT INTO user VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.execute();
            connection.close();

            System.out.println("Registration Successful");

            HelloApplication.changeScene("loginPage");
            System.out.println("Move to loginPage");


        }catch (SQLException e){
            System.out.println("Failed to registered in user");
            e.printStackTrace();
        }

    }

}
