package bd.edu.seu.tcms.model;

import java.time.LocalDateTime;

public class Sale {
    private int saleId;
    private String userName;
    private LocalDateTime saleDate;
    private double totalAmount;

    public Sale(int saleId, String userName, LocalDateTime saleDate, double totalAmount) {
        this.saleId = saleId;
        this.userName = userName;
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
    }

    public int getSaleId() { return saleId; }
    public String getUserName() { return userName; }
    public LocalDateTime getSaleDate() { return saleDate; }
    public double getTotalAmount() { return totalAmount; }
}