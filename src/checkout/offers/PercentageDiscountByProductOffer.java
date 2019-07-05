package checkout.offers;

import checkout.Check;
import checkout.Product;
import checkout.condition.ConditionByProduct;
import checkout.discount.DiscountStrategy;
import checkout.discount.PercentageDiscountByProduct;

import java.time.LocalDate;

public class PercentageDiscountByProductOffer extends Offer {
    DiscountStrategy discountStrategy;

    public PercentageDiscountByProductOffer(LocalDate expiration, Product product) {
        super(expiration, new ConditionByProduct(product));
        this.discountStrategy = new PercentageDiscountByProduct(product);
    }

    @Override
    void applyOffer(Check check) {
        check.addDiscount(discountStrategy.getDiscount(check));
    }

}
