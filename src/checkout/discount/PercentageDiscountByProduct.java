package checkout.discount;

import checkout.Check;
import checkout.Discount;
import checkout.Product;

public class PercentageDiscountByProduct implements DiscountStrategy{
    final Product product;

    public PercentageDiscountByProduct(Product product) {
        this.product = product;
    }

    @Override
    public Discount getDiscount(Check check) {
        int  priceReduction  = getPriceReduction(check);
        Discount discount = new Discount(product, priceReduction);
        return discount;
    }

    int getPriceReduction(Check check){
        return check.getTotalCostByCondition(p -> p == product)/2;
    }
}
