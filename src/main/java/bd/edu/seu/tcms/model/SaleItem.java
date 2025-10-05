package bd.edu.seu.tcms.model;

public class SaleItem {
    private String chocolateName;
    private int quantity;
    private double priceAtSale;

    public SaleItem(String chocolateName, int quantity, double priceAtSale) {
        this.chocolateName = chocolateName;
        this.quantity = quantity;
        this.priceAtSale = priceAtSale;
    }

    public String getChocolateName() { return chocolateName; }
    public int getQuantity() { return quantity; }
    public double getPriceAtSale() { return priceAtSale; }
}