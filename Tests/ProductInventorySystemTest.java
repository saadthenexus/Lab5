package Tests;

import src.OrderManager;
import src.Product;
import src.ProductFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class ProductInventorySystemTest {

    private List<Product> products;
    private OrderManager manager;

    @BeforeEach
    public void setUp() {
        products = new ArrayList<>();
        products.add(ProductFactory.createProduct("electronics", "Laptop", 10, 1000));  // Total price: 1150
        products.add(ProductFactory.createProduct("clothing", "Jeans", 50, 40));  // Total price: 44
        products.add(ProductFactory.createProduct("furniture", "Chair", 20, 150));  // Total price: 162
        products.add(ProductFactory.createProduct("electronics", "Phone", 30, 800));  // Total price: 920
        products.add(ProductFactory.createProduct("clothing", "T-shirt", 100, 20));  // Total price: 22
        products.add(ProductFactory.createProduct("furniture", "Table", 15, 200));  // Total price: 216
        products.add(ProductFactory.createProduct("electronics", "Camera", 5, 500));  // Total price: 575
        products.add(ProductFactory.createProduct("clothing", "Jacket", 25, 80));  // Total price: 88
        products.add(ProductFactory.createProduct("furniture", "Sofa", 8, 600));  // Total price: 648
        products.add(ProductFactory.createProduct("electronics", "Headphones", 100, 150));  // Total price: 172.5

        manager = new OrderManager(products);
    }

    @Test
    public void testSortingByTotalPrice() {
        manager.sortProducts();
        assertEquals("T-shirt", manager.getProducts().get(0).getName());  // T-shirt has the lowest total price (22)
        assertEquals("Jeans", manager.getProducts().get(1).getName());  // Jeans has the second lowest price (44)
        assertEquals("Jacket", manager.getProducts().get(2).getName());  // Total price 88
    }

    @Test
    public void testElectronicsTotalPrice() {
        Product laptop = ProductFactory.createProduct("electronics", "Laptop", 10, 1000);
        assertEquals(1150, laptop.calculateTotalPrice(), 0.01);  // 15% tax on 1000
    }

    @Test
    public void testClothingTotalPrice() {
        Product jeans = ProductFactory.createProduct("clothing", "Jeans", 50, 40);
        assertEquals(44, jeans.calculateTotalPrice(), 0.01);  // 10% tax on 40
    }

    @Test
    public void testFurnitureTotalPrice() {
        Product chair = ProductFactory.createProduct("furniture", "Chair", 20, 150);
        assertEquals(162, chair.calculateTotalPrice(), 0.01);  // 8% tax on 150
    }

    @Test
    public void testEmptyProductList() {
        OrderManager emptyManager = new OrderManager(new ArrayList<>());
        emptyManager.sortProducts();
        assertTrue(emptyManager.getProducts().isEmpty());  // List should remain empty
    }

    @Test
    public void testLargeQuantityOfProducts() {
        products = new ArrayList<>();
        manager = new OrderManager(products);

    
        for (int i = 0; i < 1000; i++) {
            String phoneName = String.format("Phone%04d", i);  // Zero-pad numbers to 4 digits
            products.add(ProductFactory.createProduct("electronics", phoneName, i, 800));
        }

        manager.sortProducts();

        assertEquals("Phone0000", manager.getProducts().get(0).getName());  // "Phone0000" should be at index 0
        assertEquals("Phone0001", manager.getProducts().get(1).getName());  // "Phone0001" should be at index 1
        assertEquals("Phone0002", manager.getProducts().get(2).getName());  // "Phone0002" should be at index 2
    }

    @Test
    public void testSortingByNameWhenPriceEqual() {
        products = new ArrayList<>();
        manager = new OrderManager(products);

        products.add(ProductFactory.createProduct("electronics", "CameraA", 5, 500));  // CameraA
        products.add(ProductFactory.createProduct("electronics", "CameraB", 5, 500));  // CameraB
    
        manager.sortProducts();

        assertEquals("CameraA", manager.getProducts().get(0).getName());  // Alphabetically first
        assertEquals("CameraB", manager.getProducts().get(1).getName());  // Alphabetically second
    }

    @Test
    public void testSortingByStockWhenNameAndPriceEqual() {
        products = new ArrayList<>();
        manager = new OrderManager(products);

        products.add(ProductFactory.createProduct("electronics", "Camera", 5, 500));  // Stock: 5
        products.add(ProductFactory.createProduct("electronics", "Camera", 1, 500));  // Stock: 1

        manager.sortProducts();

        assertEquals(1, manager.getProducts().get(0).getStockQuantity());  // Stock 1 first
        assertEquals(5, manager.getProducts().get(1).getStockQuantity());  // Stock 5 second
    }

        
}
