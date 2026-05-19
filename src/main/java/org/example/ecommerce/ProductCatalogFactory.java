package org.example.ecommerce;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalogFactory {

    private static final Map<String, BaseProduct> cache = new HashMap<>();

    public static BaseProduct getBaseProduct(String brand, String material, String careInstructions, String baseDescription) {
        String key = brand + "_" + material;
        if (!cache.containsKey(key)) {
            cache.put(key, new BaseProduct(brand, material, careInstructions, baseDescription));
        }
        return cache.get(key);
    }

    public static int getCacheSize() {
        return cache.size();
    }

    public static void clearCache() {
        cache.clear();
    }
}