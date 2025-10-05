package bd.edu.seu.tcms.model;

import java.time.LocalDate;

public class Chocolate {
    private int id;
    private String name;
    private double weight;
    private int quantity;
    private double price;
    private String produceDate;
    private String expiryDate;
    private String imagePath;

    public Chocolate(int id, String name, double weight, int quantity, double price, String produceDate, String expiryDate, String imagePath) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.quantity = quantity;
        this.price = price;
        this.produceDate = produceDate;
        this.expiryDate = expiryDate;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
