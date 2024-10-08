import java.util.Collections;
import java.util.List;

class ProductSorter {
    public static void sortProducts(List<Product> products) {
        Collections.sort(products, new ProductComparator());
    }
}
