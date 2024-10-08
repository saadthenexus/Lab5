package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
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
        products.add(ProductFactory.createProduct("furniture", "Sofa", 8, 600));
        products.add(ProductFactory.createProduct("electronics", "Headphones", 100, 150));

        manager = new OrderManager(products);
    }

    @Test
    public void testSortingByNameWhenPriceEqual() {
        products.add(ProductFactory.createProduct("electronics", "Camera", 5, 500)); 
        manager.sortProducts();
        assertEquals("Camera", manager.getProducts().get(3).name); 
    }

}
