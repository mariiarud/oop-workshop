package checkout.discount;

import checkout.Check;
import checkout.Discount;
import checkout.Product;
import checkout.Trademark;

public class PercentageDiscountByTrademark implements DiscountStrategy {
    final Trademark trademark;
    Product product;
    public PercentageDiscountByTrademark(Trademark trademark) {
        this.trademark = trademark;
    }

    @Override
    public Discount getDiscount(Check check) {
        product = getProductByTrademark(check);
        int  priceReduction  = getPriceReduction(check);
        Discount discount = new Discount(product, priceReduction);
        return discount;
    }

    int getPriceReduction(Check check){
        return check.getTotalCostByCondition(p -> p == product)/2;
    }

    Product getProductByTrademark(Check check){ return check.getProductByTrademark(trademark); }
}
