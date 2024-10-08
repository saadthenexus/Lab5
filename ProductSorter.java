import java.util.Collections;
import java.util.List;

class ProductSorter {
    public void sortProducts(List<Product> products, ProductComparator comparator) {
        Collections.sort(products, comparator);
    }
}
