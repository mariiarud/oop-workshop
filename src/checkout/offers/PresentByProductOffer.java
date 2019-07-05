package checkout.offers;

import checkout.Check;
import checkout.Product;
import checkout.condition.ConditionByProduct;
import checkout.discount.DiscountStrategy;
import checkout.discount.PresentByProduct;

import java.time.LocalDate;

public class PresentByProductOffer extends Offer {
    DiscountStrategy discountStrategy;

    public PresentByProductOffer(LocalDate expiration, Product product) {
        super(expiration, new ConditionByProduct(product));
        this.discountStrategy = new PresentByProduct(product);
    }

    @Override
    void applyOffer(Check check) {
        check.addDiscount(discountStrategy.getDiscount(check));
    }

}
