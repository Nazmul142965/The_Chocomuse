package bd.edu.seu.tcms.model;

public class CartItem {
    private int id;
    private String name;
    private int quantity;
    private double weight;
    private double price;
    private String imagePath;

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public CartItem(int id, String name, int quantity, double weight, double price, String imagePath) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.imagePath = imagePath;
        this.weight = weight;

    }
}
