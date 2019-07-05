package checkout.discount;

import checkout.Check;
import checkout.Discount;
import checkout.Product;

public class PresentByProduct implements DiscountStrategy {
    final Product product;

    public PresentByProduct(Product product) {
        this.product = product;
    }

    @Override
    public Discount getDiscount(Check check) {
        int  priceReduction  = getPriceReduction(check);
        Discount discount = new Discount(product, priceReduction);
        return discount;
    }

    int getPriceReduction(Check check){
        return check.getCostOfSpecificProduct(product)-1;
    }
}
