package org.example.ecommerce;

import org.example.log.ILogger;

public class ProductVariant {

    private final String sku;
    private final String size;
    private final String color;
    private final double price;
    private final int stock;
    private final BaseProduct baseProduct;

    public ProductVariant(String sku, String size, String color, double price, int stock, BaseProduct baseProduct) {
        this.sku = sku;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stock = stock;
        this.baseProduct = baseProduct;
    }

    public void render(ILogger logger) {
        baseProduct.display(sku, size, color, price, stock, logger);
    }
}