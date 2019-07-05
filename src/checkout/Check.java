package checkout;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Check {
    private List<Product> products = new ArrayList<>();
    private int points = 0;
    private List<Discount> discounts = new ArrayList<>();

    public int getTotalCost() {
        int totalCost = 0;
        for (Product product : this.products) {
            totalCost += product.price;
        }
        totalCost -= getTotalDiscount();
        return totalCost;
    }

    void addProduct(Product product) {
        products.add(product);
    }

    public int getTotalPoints() {
        return getTotalCost() + points;
    }

    public void addPoints(int points) { this.points += points; }

    public void addDiscount(Discount discount){
        discounts.add(discount);
    }

    int getTotalDiscount(){
        return discounts.stream()
                .mapToInt(d -> d.priceReduction)
                .reduce( 0, (a, b) -> a + b);
    }

    public int getTotalCostByCondition(Predicate<Product> productPredicate) {
        return products.stream()
                .filter(productPredicate)
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
    }

    public int getCostOfSpecificProduct(Product product) {
        return products.stream()
                .filter(p -> p == product)
                .mapToInt(p -> p.price)
                .findFirst()
                .orElse(0);
    }

    public Product getProductByTrademark(Trademark trademark) {
        return products.stream()
                .filter(p -> p.trademark == trademark)
                .findFirst()
                .orElse(null);
    }

    public boolean verifyIfProductIsInCheck(Predicate<Product> productPredicate) {
        return products.stream()
                .anyMatch(productPredicate);
    }

    public boolean verifyIfPresentIsAvailable(Trademark trademark) {
        long l = products.stream()
                .filter(p -> p.trademark == trademark)
                .count();
        return (l>2);
    }
}
