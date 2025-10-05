package bd.edu.seu.tcms.user;

import bd.edu.seu.tcms.HelloApplication;
import bd.edu.seu.tcms.model.CartItem;
import bd.edu.seu.tcms.model.Chocolate;
import bd.edu.seu.tcms.services.ChocolateService;
import bd.edu.seu.tcms.utility.UserSession;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class UserBuyItem implements Initializable {

    @FXML
    private TableColumn<Chocolate, String> ImageColumn;

    @FXML
    private TableColumn<Chocolate, String> expiryDateColumn;

    @FXML
    private TextField idField;

    @FXML
    private TableColumn<Chocolate, String> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TableView<Chocolate> userBuyTableView;

    @FXML
    private TableColumn<Chocolate, Number> priceColumn;

    @FXML
    private TableColumn<Chocolate, Number> quantityColumn;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField searchField;

    @FXML
    private ImageView userImageShow;

    @FXML
    private Button addToCartButton;

    @FXML
    private Label quantityErrorLabel;


    private static ObservableList<Chocolate> userChocolatesObservableList = FXCollections.observableArrayList();
    public static ObservableList<CartItem> shoppingCart = FXCollections.observableArrayList();
    private Chocolate selectedProduct;

    @FXML
    void addCartEvent(ActionEvent event) {
        if (selectedProduct == null) {
            showAlert("Error", "Please select a product first.");
            return;
        }
        String quantityText = quantityField.getText();
        if (quantityText.isEmpty()) {
            showAlert("Error", "Please enter a quantity.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                showAlert("Error", "Quantity must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid quantity format.");
            return;
        }

        if (quantity > selectedProduct.getQuantity()) {
            showAlert("Error", "Not enough items in stock.");
            return;
        }

        shoppingCart.add(new CartItem(selectedProduct.getId(), selectedProduct.getName(), quantity,selectedProduct.getWeight(), selectedProduct.getPrice(),  selectedProduct.getImagePath()));
        showAlert("Success", quantity + " of " + selectedProduct.getName() + " added to your cart.");

        clearFormAndSelection();
    }

    private void showAlert(String title, String content) {
        Alert.AlertType type = title.equalsIgnoreCase("success") ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR;
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFormAndSelection() {
        selectedProduct = null;
        idField.clear();
        nameField.clear();
        weightField.clear();
        quantityField.clear();
        userImageShow.setImage(null);
        quantityField.setDisable(true);

    }



    @FXML
    void searchEvent(KeyEvent event) {
        String search = searchField.getText().trim().toLowerCase();

        if (search.isEmpty()) {
            userBuyTableView.setItems(userChocolatesObservableList);
            return;
        }

        ObservableList<Chocolate> filteredChocolates = FXCollections.observableArrayList();
        for (Chocolate chocolate : userChocolatesObservableList) {
            if (String.valueOf(chocolate.getId()).toLowerCase().contains(search.toLowerCase())
                    || chocolate.getName().toLowerCase().contains(search.toLowerCase())
                    || String.valueOf(chocolate.getWeight()).toLowerCase().contains(search.toLowerCase())
                    || String.valueOf(chocolate.getPrice()).toLowerCase().contains(search.toLowerCase())) {
                filteredChocolates.add(chocolate);
            }
        }

        userBuyTableView.setItems(filteredChocolates);
    }

    @FXML
    void userBuyItemEvent(ActionEvent event) {
        HelloApplication.changeScene("UserBuyItem");
        System.out.println("UserBuy Item Called");

    }

    @FXML
    void userDashboardEvent(ActionEvent event) {
        HelloApplication.changeScene("userDashboard");
        System.out.println("User Dashboard called");

    }

    @FXML
    void userInventoryEvent(ActionEvent event) {
        HelloApplication.changeScene("userInventory");
        System.out.println("User Inventory called");

    }

    @FXML
    void userPayBillEvent(ActionEvent event) {
        HelloApplication.changeScene("userPayBill");
        System.out.println("User Pay Bill called");
    }

    @FXML
    void userSignOutEvent(ActionEvent event) {
        UserBuyItem.shoppingCart.clear();
        UserSession.logoutUser();
        HelloApplication.changeScene("welcomePage");
        System.out.println("User SingOut called");

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableMap();
        defaultTableView();


        userBuyTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.selectedProduct = newSelection;
                showData(newSelection);

                quantityField.setDisable(false);
            } else {
                clearFormAndSelection();
            }
        });

        setupQuantityValidation();

        quantityField.setDisable(true);
    }

    private void defaultTableView() {
        ChocolateService chocolateService = new ChocolateService();
        userChocolatesObservableList.setAll(chocolateService.getChocolatesObservableList());

    }

    private void tableMap() {
        nameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        priceColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getPrice()));
        expiryDateColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getExpiryDate()));
        quantityColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getQuantity()));
        ImageColumn.setCellValueFactory(new PropertyValueFactory<>("imagePath"));
        ImageColumn.setCellFactory(param -> new TableCell<Chocolate, String>() {
            private final ImageView imageViewInCell = new ImageView();

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);

                if (empty || imagePath == null || imagePath.isEmpty()) {
                    setGraphic(null);
                } else {
                    File file = new File(imagePath);
                    if (file.exists()) {
                        Image image = new Image(file.toURI().toString());
                        imageViewInCell.setImage(image);
                        imageViewInCell.setFitHeight(40);
                        imageViewInCell.setFitWidth(40);
                        setGraphic(imageViewInCell);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });
        userBuyTableView.setItems(userChocolatesObservableList);

    }

    private void showData(Chocolate chocolate) {
        idField.setText(String.valueOf(chocolate.getId()));
        idField.setDisable(true);

        nameField.setText(chocolate.getName());
        nameField.setDisable(true);
        weightField.setText(String.valueOf(chocolate.getWeight()));
        weightField.setDisable(true);
        File imageFile = new File(chocolate.getImagePath());
        if (imageFile.exists()) {
            userImageShow.setImage(new Image(imageFile.toURI().toString()));
        } else {
            userImageShow.setImage(null);
            System.err.println("Image not found at path: " + chocolate.getImagePath());
        }
        quantityField.clear();
        quantityErrorLabel.setVisible(false);
        addToCartButton.setDisable(false);
        quantityField.setStyle("");
    }

    private void setupQuantityValidation() {
        quantityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (selectedProduct == null || newValue.isEmpty()) {
                quantityErrorLabel.setVisible(false);
                addToCartButton.setDisable(false);
                quantityField.setStyle("");
                return;
            }
            try {
                int requestedQuantity = Integer.parseInt(newValue);
                if (requestedQuantity > selectedProduct.getQuantity()) {
                    quantityErrorLabel.setText("Stock: " + selectedProduct.getQuantity());
                    quantityErrorLabel.setVisible(true);
                    addToCartButton.setDisable(true);
                    quantityField.setStyle("-fx-border-color: red;");
                } else {
                    quantityErrorLabel.setVisible(false);
                    addToCartButton.setDisable(false);
                    quantityField.setStyle("");
                }
            } catch (NumberFormatException e) {
                quantityErrorLabel.setText("Invalid number");
                quantityErrorLabel.setVisible(true);
                addToCartButton.setDisable(true);
                quantityField.setStyle("-fx-border-color: red;");
            }
        });
    }
}
