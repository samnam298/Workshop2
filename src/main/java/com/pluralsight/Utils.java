package com.pluralsight;

import java.io.*;
import java.util.*;

public class Utils {
    public static List<Product> loadProducts(String filePath) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    products.add(new Product(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading products: " + e.getMessage());
        }
        return products;
    }

    public static Product findBySku(List<Product> products, String sku) {
        for (Product p : products)
            if (p.getSku().equalsIgnoreCase(sku)) return p;
        return null;
    }
}
