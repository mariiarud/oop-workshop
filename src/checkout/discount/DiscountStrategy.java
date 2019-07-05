package checkout.discount;

import checkout.Check;
import checkout.Discount;

public interface DiscountStrategy {
    Discount getDiscount(Check check);
}
