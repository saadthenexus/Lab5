package src;
import java.util.List;

public class ProductDisplay {
    public void displayProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }
}