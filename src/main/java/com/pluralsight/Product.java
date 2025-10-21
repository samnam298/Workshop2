package com.pluralsight;

public class Product {
    private String sku;
    private String name;
    private double price;
    private String department;

    public Product(String sku, String name, double price, String department) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.department = department;
    }

    public String getSku() { return sku; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return String.format("%-8s | %-25s | $%-8.2f | %-15s", sku, name, price, department);
    }
}
