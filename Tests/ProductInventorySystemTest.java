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
    public void testSortingByTotalPrice() {
        manager.sortProducts();
        assertEquals("T-shirt", manager.getProducts().get(0).getName()); 
        assertEquals("Jeans", manager.getProducts().get(1).getName());  
        assertEquals("Jacket", manager.getProducts().get(2).getName());  
    }

    @Test
    public void testElectronicsTotalPrice() {
        Product laptop = ProductFactory.createProduct("electronics", "Laptop", 10, 1000);
        assertEquals(1150, laptop.calculateTotalPrice(), 0.01);  
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
        assertTrue(emptyManager.getProducts().isEmpty()); 
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

    @Test
    public void testDifferentUnitsForSameProduct() {
        products = new ArrayList<>();
        manager = new OrderManager(products);

        products.add(ProductFactory.createProduct("electronics", "Laptop", 5, 1000));  // Stock 5
        products.add(ProductFactory.createProduct("electronics", "Laptop", 10, 1000)); // Stock 10

        manager.sortProducts();

        assertEquals(5, manager.getProducts().get(0).getStockQuantity());  // Stock 5 first
        assertEquals(10, manager.getProducts().get(1).getStockQuantity()); // Stock 10 second
    }

    @Test
    public void testProductWithZeroStock() {
        products = new ArrayList<>();
        manager = new OrderManager(products);

        Product zeroStockProduct = ProductFactory.createProduct("electronics", "Tablet", 0, 300);
        products.add(zeroStockProduct);

        products.add(ProductFactory.createProduct("electronics", "Phone", 5, 250)); // Total price: 250 + 15% tax

        manager.sortProducts();

        assertEquals("Phone", manager.getProducts().get(0).getName());  
        assertEquals("Tablet", manager.getProducts().get(1).getName());  
    }

        
}
