package com.pluralsight;

import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OnlineStore {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DATA_FILE = "data/products.csv";
    private static final String RECEIPTS_DIR = "receipts/";

    public static void main(String[] args) {
        List<Product> products = Utils.loadProducts(DATA_FILE);
        Cart cart = new Cart();
        new File(RECEIPTS_DIR).mkdirs();

        boolean running = true;
        while (running) {
            System.out.println("\n===== 🏪 ONLINE STORE =====");
            System.out.println("1. Display Products");
            System.out.println("2. View Cart");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            switch (scanner.nextLine()) {
                case "1" -> showProducts(products, cart);
                case "2" -> showCart(cart);
                case "3" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
        System.out.println("Thanks for visiting our store! 👋");
    }

    private static void showProducts(List<Product> products, Cart cart) {
        System.out.println("\n===== 🛍️ PRODUCTS =====");
        for (Product p : products) System.out.println(p);
        System.out.print("\nEnter SKU to add to cart (or 'back'): ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("back")) return;
        Product found = Utils.findBySku(products, input);
        if (found == null) System.out.println("⚠️ Product not found.");
        else {
            System.out.print("Enter quantity: ");
            int qty = Integer.parseInt(scanner.nextLine());
            cart.addItem(found, qty);
            System.out.println("✅ Added to cart!");
        }
    }

    private static void showCart(Cart cart) {
        cart.displayCart();
        if (cart.isEmpty()) return;

        System.out.print("\n1. Check Out\n2. Back: ");
        String choice = scanner.nextLine();
        if (choice.equals("1")) checkout(cart);
    }

    private static void checkout(Cart cart) {
        double total = cart.getTotal();
        System.out.printf("Amount due: $%.2f\nEnter cash amount: $", total);
        double paid = Double.parseDouble(scanner.nextLine());
        if (paid < total) {
            System.out.println("❌ Insufficient payment!");
            return;
        }
        double change = paid - total;
        System.out.printf("Change: $%.2f\n", change);
        printReceipt(cart, paid, change);
        cart.clear();
        System.out.println("✅ Checkout complete. Returning to home screen...");
    }

    private static void printReceipt(Cart cart, double paid, double change) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = RECEIPTS_DIR + timestamp + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("===== SALES RECEIPT =====");
            for (CartItem item : cart.getItems()) writer.println(item);
            writer.printf("\nTOTAL: $%.2f\nPaid: $%.2f\nChange: $%.2f\n", cart.getTotal(), paid, change);
            writer.println("=========================");
        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
}
