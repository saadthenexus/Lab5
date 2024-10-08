import java.util.List;

public class OrderManager {
    private List<Product> productList;

    public OrderManager(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProducts() {
        return productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void sortProducts(){
        ProductSorter.sortProducts(productList);
    }
}
