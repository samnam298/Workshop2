package com.pluralsight;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void addQuantity(int amount) { this.quantity += amount; }
    public double getTotal() { return product.getPrice() * quantity; }

    @Override
    public String toString() {
        return String.format("%-25s x%-3d $%-8.2f", product.getName(), quantity, getTotal());
    }
}


