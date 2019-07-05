package checkout.offers;

import checkout.Check;
import checkout.discount.DiscountStrategy;
import checkout.discount.PercentageDiscountByTrademark;
import checkout.Trademark;
import checkout.condition.ConditionByTrademark;

import java.time.LocalDate;

public class PercentageDiscountByTrademarkOffer extends Offer {
    DiscountStrategy discountStrategy;

    public PercentageDiscountByTrademarkOffer(LocalDate expiration, Trademark trademark) {
        super(expiration, new ConditionByTrademark(trademark));
        this.discountStrategy = new PercentageDiscountByTrademark(trademark);
    }

    @Override
    void applyOffer(Check check) {
        check.addDiscount(discountStrategy.getDiscount(check));
    }

}
