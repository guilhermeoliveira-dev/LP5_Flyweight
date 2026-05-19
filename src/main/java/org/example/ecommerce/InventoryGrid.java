package org.example.ecommerce;

import org.example.log.ILogger;
import java.util.ArrayList;
import java.util.List;

public class InventoryGrid {

    private final List<ProductVariant> variants = new ArrayList<>();
    private final ILogger logger;

    public InventoryGrid(ILogger logger) {
        this.logger = logger;
    }

    public void addVariant(String sku, String size, String color, double price, int stock,
                           String brand, String material, String careInstructions, String baseDescription) {

        BaseProduct base = ProductCatalogFactory.getBaseProduct(brand, material, careInstructions, baseDescription);
        variants.add(new ProductVariant(sku, size, color, price, stock, base));
    }

    public void displayGrid() {
        for (ProductVariant variant : variants) {
            variant.render(logger);
        }
    }

    public int getVariantCount() {
        return variants.size();
    }
}