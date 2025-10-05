package bd.edu.seu.tcms.services;

import bd.edu.seu.tcms.interfaces.ChocolateInterface;
import bd.edu.seu.tcms.model.Chocolate;
import bd.edu.seu.tcms.utility.ConnectionSingleton;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ChocolateService implements ChocolateInterface {
    @Override
    public void insertChocolateDetails(Chocolate chocolate) {
        try{
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO chocolate VALUES (?, ?, ?, ?, ?, ?, ? ,?)");
            preparedStatement.setInt(1, chocolate.getId());
            preparedStatement.setString(2,chocolate.getName());
            preparedStatement.setDouble(3,chocolate.getWeight());
            preparedStatement.setInt(4,chocolate.getQuantity());
            preparedStatement.setDouble(5,chocolate.getPrice());
            preparedStatement.setString(6,  chocolate.getProduceDate());
            preparedStatement.setString(7, chocolate.getExpiryDate());
            preparedStatement.setString(8, chocolate.getImagePath());
            preparedStatement.executeUpdate();
            System.out.println("Chocolate Details Inserted to database.");
        }catch(SQLException e){
            System.out.println("Failed to insert Book");
            e.printStackTrace();
            }
    }

    @Override
    public void updateChocolateDetails(Chocolate oldChocolate, Chocolate updatedChocolate) {
        try{
            Connection connection = ConnectionSingleton.getConnection();
            String queryUpdate = "UPDATE chocolate SET id = ?, name = ?, weight = ?, quantity = ?, price = ?, produceDate = ?, expiryDate = ?, imagePath = ? " +
                    "WHERE id = ? AND name = ? AND weight = ? AND quantity = ? AND price = ? AND produceDate = ? AND expiryDate = ? AND imagePath = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate);
            preparedStatement.setInt(1, updatedChocolate.getId());
            preparedStatement.setString(2,updatedChocolate.getName());
            preparedStatement.setDouble(3,updatedChocolate.getWeight());
            preparedStatement.setInt(4,updatedChocolate.getQuantity());
            preparedStatement.setDouble(5,updatedChocolate.getPrice());
            preparedStatement.setString(6, updatedChocolate.getProduceDate());
            preparedStatement.setString(7, updatedChocolate.getExpiryDate());
            preparedStatement.setString(8, updatedChocolate.getImagePath());
            preparedStatement.setInt(9, oldChocolate.getId());
            preparedStatement.setString(10, oldChocolate.getName());
            preparedStatement.setDouble(11, oldChocolate.getWeight());
            preparedStatement.setInt(12, oldChocolate.getQuantity());
            preparedStatement.setDouble(13, oldChocolate.getPrice());
            preparedStatement.setString(14, oldChocolate.getProduceDate());
            preparedStatement.setString(15, oldChocolate.getExpiryDate());
            preparedStatement.setString(16, oldChocolate.getImagePath());

            preparedStatement.executeUpdate();
            System.out.println("Chocolate Details Updated to database.");
        }catch (SQLException e){
            System.out.println("Failed to update Book");
            e.printStackTrace();
        }


    }

    @Override
    public void deleteChocolateDetails(Chocolate chocolate) {
        try{
            Connection connection = ConnectionSingleton.getConnection();
            String queryDelete ="DELETE FROM chocolate WHERE id = ? ";;

            PreparedStatement preparedStatement = connection.prepareStatement(queryDelete);
            preparedStatement.setInt(1, chocolate.getId());

            preparedStatement.executeUpdate();
            System.out.println("Chocolate Details Deleted From database.");

        }catch(SQLException e){
            System.out.println("Failed to delete Book");
            e.printStackTrace();
        }
    }

    @Override
    public List<Chocolate> getChocolatesObservableList() {
        List<Chocolate> chocolatesObservableList = new ArrayList<>();
        try{
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM chocolate ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Chocolate chocolate = new Chocolate(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(4),
                        resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
                        chocolatesObservableList.add(chocolate);
            }

        }catch (SQLException ex){
            System.out.println("Failed to get Chocolates");
            ex.printStackTrace();
        }

        return chocolatesObservableList;
    }

}
