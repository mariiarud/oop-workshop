package checkout;

import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<Product> products = new ArrayList<>();
    private int points = 0;
    private int discounts = 0;

    public int getTotalCost() {
        int totalCost = 0;
        for (Product product : this.products) {
            totalCost += product.price;
        }
        totalCost -= discounts;
        return totalCost;
    }

    void addProduct(Product product) {
        products.add(product);
    }

    public int getTotalPoints() {
        return getTotalCost() + points;
    }

    void addPoints(int points) {
        this.points += points;
    }

    int getCostByCategory(Category category) {
        return products.stream()
                .filter(p -> p.category == category)
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
    }

    void addDiscount(Trademark trademark){
        discounts += products.stream()
                    .filter(p -> p.trademark == trademark)
                    .mapToInt(p -> (p.price/2))
                    .reduce(0, (a, b) -> a + b);
    }

    void addDiscount(Product product){
        discounts += products.stream()
                .filter(p -> p == product)
                .mapToInt(p -> (p.price/2))
                .reduce(0, (a, b) -> a + b);
    }

    void addPresent(Trademark trademark) {
        int n = 0;
        for(Product product: products){
            if(product.trademark == trademark){
                n++;
                if(n>2){
                    discounts+=product.price;
                    n=0;
                }
            }
        }
    }

    void addPresent(Product product) {
        int n = 0;
        for(Product p: products){
            if(p == product){
                n++;
                if(n>2){
                    discounts+=product.price;
                    n=0;
                }
            }
        }
    }
}
