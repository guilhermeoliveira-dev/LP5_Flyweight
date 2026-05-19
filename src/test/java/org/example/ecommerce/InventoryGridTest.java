package org.example.ecommerce;

import org.example.log.ILogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InventoryGridTest {

    private TestLogger spyLogger;
    private InventoryGrid grid;

    private static class TestLogger implements ILogger {
        private final List<String> logs = new ArrayList<>();

        @Override
        public void log(String text) {
            logs.add(text);
        }

        public boolean containsLog(String snippet) {
            return logs.stream().anyMatch(log -> log.contains(snippet));
        }

        public int getLogCount() {
            return logs.size();
        }
    }

    @BeforeEach
    void setUp() {
        spyLogger = new TestLogger();
        grid = new InventoryGrid(spyLogger);
        ProductCatalogFactory.clearCache();
    }

    @Test
    void givenMultipleVariantsOfSameProduct_whenAdded_thenFactoryReusesBaseProduct() {
        grid.addVariant("TSH-BLK-S", "S", "Black", 19.99, 50, "Acme", "Cotton", "Wash cold", "Basic Tee");
        grid.addVariant("TSH-BLK-M", "M", "Black", 19.99, 30, "Acme", "Cotton", "Wash cold", "Basic Tee");
        grid.addVariant("TSH-WHT-L", "L", "White", 19.99, 10, "Acme", "Cotton", "Wash cold", "Basic Tee");

        assertEquals(3, grid.getVariantCount());
        assertEquals(1, ProductCatalogFactory.getCacheSize());
    }

    @Test
    void givenVariantsOfDifferentProducts_whenAdded_thenFactoryCreatesDistinctBaseProducts() {
        grid.addVariant("TSH-BLK-S", "S", "Black", 19.99, 50, "Acme", "Cotton", "Wash cold", "Basic Tee");
        grid.addVariant("JNS-BLU-32", "32", "Blue", 49.99, 15, "DenimCo", "Denim", "Wash warm", "Classic Jeans");

        assertEquals(2, grid.getVariantCount());
        assertEquals(2, ProductCatalogFactory.getCacheSize());
    }

    @Test
    void givenVariantsInGrid_whenDisplayGrid_thenLoggerReceivesCorrectOutputs() {
        grid.addVariant("TSH-BLK-S", "S", "Black", 19.99, 50, "Acme", "Cotton", "Wash cold", "Basic Tee");
        grid.addVariant("JNS-BLU-32", "32", "Blue", 49.99, 15, "DenimCo", "Denim", "Wash warm", "Classic Jeans");

        grid.displayGrid();

        assertEquals(2, spyLogger.getLogCount());
        assertTrue(spyLogger.containsLog("SKU: TSH-BLK-S | Acme (Cotton) | Black, Size S | $19.99 | Stock: 50"));
        assertTrue(spyLogger.containsLog("SKU: JNS-BLU-32 | DenimCo (Denim) | Blue, Size 32 | $49.99 | Stock: 15"));
    }
}