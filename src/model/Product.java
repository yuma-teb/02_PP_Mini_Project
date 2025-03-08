package model;

import java.time.LocalDate;

public class Product {
    private final int id;
    private String name;
    private double unitPrice;
    private int qty;
    private LocalDate importDate;

    public Product(int id, String name, double unitPrice, int qty, LocalDate importDate) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.importDate = importDate;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnitPrice() {
        return unitPrice+"";
    }

    public String getQty() {
        return qty+"";
    }

    public String getImportDate() {
        return importDate+"";
    }

    @Override
    public String toString() {
        return "1. Name \t\t" +
                "2. Unit Price\t\t" +
                "3. Qty \t\t"+
                "4. All Field \t\t"+
                "5. Exit";
    }
}
