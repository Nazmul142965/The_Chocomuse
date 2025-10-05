package bd.edu.seu.tcms.admin;

import bd.edu.seu.tcms.HelloApplication;
import bd.edu.seu.tcms.model.Chocolate;
import bd.edu.seu.tcms.services.ChocolateService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;


import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminInventory implements Initializable {

    @FXML
    private TableColumn<Chocolate, String> expiryDateColumn;

    @FXML
    private TableColumn<Chocolate, Number> idColumn;

    @FXML
    private ImageView inventoryImageView;

    @FXML
    private DatePicker inventoryExpiryDateField;

    @FXML
    private TextField inventoryIdField;

    @FXML
    private TextField inventoryNameField;

    @FXML
    private TextField inventoryPriceField;

    @FXML
    private DatePicker inventoryProduceDateField;

    @FXML
    private TextField inventoryQuantityField;

    @FXML
    private TextField inventoryWeightField;

    @FXML
    private TableView<Chocolate> inventoryTableView;

    @FXML
    private TableColumn<Chocolate, String> nameColumn;

    @FXML
    private TableColumn<Chocolate, Number> priceColumn;

    @FXML
    private TableColumn<Chocolate, Number> quantityColumn;

    @FXML
    private Button updateImageButton;

    @FXML
    private Button inventoryUpdateButton;

    @FXML
    private Button inventoryDeleteButton;

    @FXML
    private TextField inventorySearchField;

    @FXML
    private TableColumn<Chocolate, Number> weightColumn;

    private static ObservableList<Chocolate> chocolatesObservableList = FXCollections.observableArrayList();
    private static Chocolate oldChocolate;
    private String newImagePath = null;
    @FXML
    void addItemEvent(ActionEvent event) {
        HelloApplication.changeScene("adminAddItem");
        System.out.println("Added Item Called\n");

    }

    @FXML
    void dashboardEvent(ActionEvent event) {
        HelloApplication.changeScene("adminDashboard");
        System.out.println("ADMIN  Dashboard Called\n");

    }

    @FXML
    void inventoryDeleteEvent(ActionEvent event) {
        Chocolate updatedChocolate =new Chocolate(oldChocolate.getId(), inventoryNameField.getText(), Double.parseDouble(inventoryWeightField.getText()),
                Integer.parseInt(inventoryQuantityField.getText()), Double.parseDouble(inventoryPriceField.getText()), inventoryProduceDateField.getAccessibleRoleDescription(),inventoryExpiryDateField.getAccessibleRoleDescription(), inventoryImageView.getImage().getUrl());

        ChocolateService chocolateService = new ChocolateService();
        chocolateService.deleteChocolateDetails(updatedChocolate);


        defaultTableView();

        inventoryUpdateButton.setVisible(false);
        inventoryDeleteButton.setVisible(false);
    }

    @FXML
    void inventoryEvent(ActionEvent event) {
        HelloApplication.changeScene("adminInventory");
        System.out.println("Inventory  Called\n");

    }


    @FXML
    void inventoryUpdateEvent(ActionEvent event) {
       Chocolate UpdateChocolate = new Chocolate(oldChocolate.getId(), inventoryNameField.getText(), Double.parseDouble(inventoryWeightField.getText()),
               Integer.parseInt(inventoryQuantityField.getText()), Double.parseDouble(inventoryPriceField.getText()), inventoryProduceDateField.getAccessibleRoleDescription(),inventoryExpiryDateField.getAccessibleRoleDescription(), inventoryImageView.getImage().getUrl() );

       ChocolateService chocolateService = new ChocolateService();
       chocolateService.updateChocolateDetails(oldChocolate,UpdateChocolate);

       defaultTableView();


       inventoryIdField.setDisable(false);
    }

    @FXML
    void selectedRowEvent(MouseEvent event) {
        Chocolate selectedChocolate = inventoryTableView.getSelectionModel().getSelectedItem();
        showData(selectedChocolate);

    }

    @FXML
    void revenueEvent(ActionEvent event) {
        HelloApplication.changeScene("adminRevenue");
        System.out.println("Admin Revenue  Called\n");

    }

    @FXML
    void searchEvent(KeyEvent event)  {
        String search = inventorySearchField.getText().trim().toLowerCase();

        if (search.isEmpty()) {
            inventoryTableView.setItems(chocolatesObservableList);
            return;
        }

        ObservableList<Chocolate> filteredChocolates = FXCollections.observableArrayList();
        for (Chocolate chocolate : chocolatesObservableList) {
            if (String.valueOf(chocolate.getId()).toLowerCase().contains(search.toLowerCase())
                    || chocolate.getName().toLowerCase().contains(search.toLowerCase())
                    || String.valueOf(chocolate.getWeight()).toLowerCase().contains(search.toLowerCase())
                    || String.valueOf(chocolate.getQuantity()).toLowerCase().contains(search.toLowerCase())
                    || String.valueOf(chocolate.getPrice()).toLowerCase().contains(search.toLowerCase())) {
                filteredChocolates.add(chocolate);
            }
        }
        inventoryTableView.setItems(filteredChocolates);
    }

    @FXML
    void signOutEvent(ActionEvent event) {
        HelloApplication.changeScene("welcomePage");
        System.out.println(" Welcome Page Called\n");

    }

    @FXML
    void inventoryUpdateImageEvent(ActionEvent event) {
        FileChooser imageChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif", "*.jpeg");
        imageChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = imageChooser.showOpenDialog(null);

        if (selectedFile != null) {
            inventoryImageView.setImage(new Image(selectedFile.toURI().toString()));
            this.newImagePath = selectedFile.getAbsolutePath(); // <-- পরিবর্তন: নতুন ছবির পাথ সেভ করা
            System.out.println("New image for update selected: " + this.newImagePath);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableMapping();

        defaultTableView();

        inventorySearchField.setOnKeyReleased(event -> searchEvent(event));
    }
    private void defaultTableView() {
        ChocolateService chocolateService = new ChocolateService();
        chocolatesObservableList.setAll(chocolateService.getChocolatesObservableList());

    }

    private void tableMapping() {
        idColumn.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getId()));
        nameColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getName()));
        weightColumn.setCellValueFactory(c-> new SimpleDoubleProperty(c.getValue().getWeight()));
        quantityColumn.setCellValueFactory(c-> new SimpleDoubleProperty(c.getValue().getQuantity()));
        priceColumn.setCellValueFactory(c-> new SimpleDoubleProperty(c.getValue().getPrice()));
        expiryDateColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getExpiryDate()));

        inventoryTableView.setItems(chocolatesObservableList);

    }

    private void showData(Chocolate chocolate) {
        oldChocolate = chocolate;
        this.newImagePath = null;

        inventoryIdField.setText(String.valueOf(chocolate.getId()));
        inventoryIdField.setDisable(true);

        inventoryIdField.setText(String.valueOf(chocolate.getId()));
        inventoryNameField.setText(chocolate.getName());
        inventoryWeightField.setText(String.valueOf(chocolate.getWeight()));
        inventoryQuantityField.setText(String.valueOf(chocolate.getQuantity()));
        inventoryPriceField.setText(String.valueOf(chocolate.getPrice()));
        inventoryProduceDateField.setValue(LocalDate.parse(chocolate.getProduceDate()));
        inventoryExpiryDateField.setValue(LocalDate.parse(chocolate.getExpiryDate()));
        File imageFile = new File(chocolate.getImagePath());
        if(imageFile.exists()){
            inventoryImageView.setImage(new Image(imageFile.toURI().toString()));
        } else {
            inventoryImageView.setImage(null);
            System.err.println("Image not found at path: " + chocolate.getImagePath());
        }
    }
}

