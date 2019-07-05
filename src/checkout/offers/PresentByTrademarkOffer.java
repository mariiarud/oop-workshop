package checkout.offers;

import checkout.Check;
import checkout.discount.DiscountStrategy;
import checkout.discount.PresentByTrademark;
import checkout.Trademark;
import checkout.condition.ConditionPresent;

import java.time.LocalDate;

public class PresentByTrademarkOffer extends Offer {
    DiscountStrategy discountStrategy;

    public PresentByTrademarkOffer(LocalDate expiration, Trademark trademark) {
        super(expiration, new ConditionPresent(trademark));
        this.discountStrategy = new PresentByTrademark(trademark);
    }

    @Override
    void applyOffer(Check check) {
        check.addDiscount(discountStrategy.getDiscount(check));
    }

}