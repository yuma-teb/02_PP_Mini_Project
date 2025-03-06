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
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", importDate=" + importDate +
                '}';
    }
}
