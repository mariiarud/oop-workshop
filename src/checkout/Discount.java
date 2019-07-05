package checkout;

public class Discount {
    final Product product;
    final int priceReduction;

    public Discount(Product product, int priceReduction){
        this.product = product;
        this.priceReduction = priceReduction;
    }
}
