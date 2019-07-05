package checkout.offers;

import checkout.Category;
import checkout.Check;
import checkout.reward.FactorReward;
import checkout.reward.Reward;
import checkout.condition.ConditionByCategory;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    Reward rewardRule;

    public FactorByCategoryOffer(Category category, int factor, LocalDate expiration) {
        super(expiration, new ConditionByCategory(category));
        this.rewardRule = new FactorReward(category, factor);
    }

    @Override
    void applyOffer(Check check) {
        check.addPoints(rewardRule.calculatePoints(check));
    }

}
