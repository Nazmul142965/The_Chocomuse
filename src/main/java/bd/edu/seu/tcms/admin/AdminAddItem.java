package bd.edu.seu.tcms.admin;

import bd.edu.seu.tcms.HelloApplication;
import bd.edu.seu.tcms.model.Chocolate;
import bd.edu.seu.tcms.services.ChocolateService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.time.LocalDate;

public class AdminAddItem {

    @FXML
    private DatePicker expireDateField;

    @FXML
    private TextField idField;

    @FXML
    private ImageView imageViewField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private DatePicker productionDateField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField weightField;

    private String selectedImagePath;
    @FXML
    void addItemEvent(ActionEvent event) {
        HelloApplication.changeScene("adminAddItem");
        System.out.println("Admin Add Item Called\n");

    }

    @FXML
    void dashboardEvent(ActionEvent event) {
        HelloApplication.changeScene("adminDashboard");
        System.out.println("Dashboard  Page Called\n");

    }

    @FXML
    void inventoryEvent(ActionEvent event) {
        HelloApplication.changeScene("adminInventory");

    }

    @FXML
    void revenueEvent(ActionEvent event) {
        HelloApplication.changeScene("adminRevenue");
        System.out.println("Revenue  Page Called\n");
    }

    @FXML
    void saveEvent(ActionEvent event) {
        if(idField.getText().isEmpty() || nameField.getText().isEmpty() || weightField.getText().isEmpty() ||
                quantityField.getText().isEmpty() || priceField.getText().isEmpty() ||
                productionDateField.getValue() == null || expireDateField.getValue() == null || this.selectedImagePath == null || this.selectedImagePath.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }else{
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double weight = Double.parseDouble(weightField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            String productionDate = productionDateField.getValue().toString();
            String expireDate = expireDateField.getValue().toString();
//            String  imagePath = imageViewField.getImage().toString();
            System.out.println(id + ", " + name + ", " + weight + ", " + quantity + ", " + price + ", " + productionDate + ", " + expireDate + ", " + selectedImagePath);
            Chocolate chocolate = new Chocolate(id , name, weight, quantity, price, productionDate, expireDate,  this.selectedImagePath);
            ChocolateService  chocolateService = new ChocolateService();
            chocolateService.insertChocolateDetails(chocolate);
            System.out.println("SaveEvent  Successful\n");
        }
    }

    @FXML
    void signOutEvent(ActionEvent event) {
        HelloApplication.changeScene("welcomePage");
        System.out.println("Sinning out, bye bye\n");

    }

    @FXML
    void updateImageEvent(ActionEvent event) {
        FileChooser imageChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif", "*.jpeg");
        imageChooser.getExtensionFilters().add(imageFilter);

        File selectedFile = imageChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageViewField.setImage(image);


            this.selectedImagePath = selectedFile.getAbsolutePath(); // <--- পরিবর্তন: ছবির পাথ এখানে সেভ করা হচ্ছে

            System.out.println("Image Uploaded Successfully\n");
        }
        }

    }




