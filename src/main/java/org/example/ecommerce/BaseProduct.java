package org.example.ecommerce;

import org.example.log.ILogger;

public class BaseProduct {

    private final String brand;
    private final String material;
    private final String careInstructions;
    private final String baseDescription;

    public BaseProduct(String brand, String material, String careInstructions, String baseDescription) {
        this.brand = brand;
        this.material = material;
        this.careInstructions = careInstructions;
        this.baseDescription = baseDescription;
    }

    public void display(String sku, String size, String color, double price, int stock, ILogger logger) {
        logger.log("SKU: " + sku + " | " + brand + " (" + material + ") | " + color + ", Size " + size + " | $" + price + " | Stock: " + stock);
    }
}