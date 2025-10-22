package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int qty) {
        for (CartItem item : items) {
            if (item.getProduct().getSku().equalsIgnoreCase(product.getSku())) {
                item.addQuantity(qty);
                return;
            }
        }
        items.add(new CartItem(product, qty));
    }

    public void clear() { items.clear(); }
    public double getTotal() {
        double total = 0;
        for (CartItem item : items) total += item.getTotal();
        return total;
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("\n🛍️  Your cart is empty.");
        } else {
            System.out.println("\n===== 🛒 YOUR CART =====");
            for (CartItem item : items) System.out.println(item);
            System.out.printf("\nTOTAL: $%.2f\n", getTotal());
        }
    }

    public boolean isEmpty() { return items.isEmpty(); }
    public List<CartItem> getItems() { return items; }
}
