package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.OrderManager;
import src.Product;
import src.ProductFactory;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;


public class ProductInventorySystemTest {

    private List<Product> products;
    private OrderManager manager;

    @BeforeEach
    public void setUp() {
        products = new ArrayList<>();
        products.add(ProductFactory.createProduct("electronics", "Laptop", 10, 1000));
        products.add(ProductFactory.createProduct("clothing", "Jeans", 50, 40));
        products.add(ProductFactory.createProduct("furniture", "Chair", 20, 150));
        products.add(ProductFactory.createProduct("electronics", "Phone", 30, 800));
        products.add(ProductFactory.createProduct("clothing", "T-shirt", 100, 20));
        products.add(ProductFactory.createProduct("furniture", "Table", 15, 200));
        products.add(ProductFactory.createProduct("electronics", "Camera", 5, 500));
        products.add(ProductFactory.createProduct("clothing", "Jacket", 25, 80));
        products.add(ProductFactory.createProduct("electronics", "Headphones", 100, 150));

        manager = new OrderManager(products);
    }

    @Test
    public void testSortingByTotalPrice() {
        manager.sortProducts();
        assertEquals("T-shirt", manager.getProducts().get(0).getName()); 
        assertEquals("Jeans", manager.getProducts().get(1).getName());
    }

    @Test
    public void testLargeQuantityOfProducts() {
        for (int i = 0; i < 1000; i++) {
            products.add(ProductFactory.createProduct("electronics", "Phone" + i, i, 800));
        }
        manager.sortProducts();
        assertEquals("Phone10", manager.getProducts().get(10).getName());  
    }

    @Test
    public void testSortingByNameWhenPriceEqual() {
        products.add(ProductFactory.createProduct("electronics", "Camera", 5, 500)); // Same price as existing Camera
        manager.sortProducts();
        assertEquals("Chair", manager.getProducts().get(3).getName());  // Alphabetical sorting
    }

    @Test
    public void testSortingByStockWhenNameAndPriceEqual() {
        products.add(ProductFactory.createProduct("electronics", "Camera", 1, 500));  // Same name and price
        manager.sortProducts();
        assertEquals(20, manager.getProducts().get(3).getStockQuantity());  // Lower stock quantity comes first
    }

}
