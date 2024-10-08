package src;
import java.util.Comparator;

class ProductComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        double totalPrice1 = p1.calculateTotalPrice();
        double totalPrice2 = p2.calculateTotalPrice();

        if (Double.compare(totalPrice1, totalPrice2) != 0) {
            return Double.compare(totalPrice1, totalPrice2);
        }

        int nameComparison = p1.getName().compareTo(p2.getName());
        if (nameComparison != 0) {
            return nameComparison;
        }

        return Integer.compare(p1.getStockQuantity(), p2.getStockQuantity());
    }
}

