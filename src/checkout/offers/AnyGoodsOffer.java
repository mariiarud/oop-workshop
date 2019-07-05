package checkout.offers;

import checkout.Check;
import checkout.reward.FlatReward;
import checkout.reward.Reward;
import checkout.condition.ConditionTotalCost;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {
    Reward rewardRule;

    public AnyGoodsOffer(LocalDate expiration, int totalCost, int points) {
        super(expiration, new ConditionTotalCost(totalCost));
        this.rewardRule = new FlatReward(points);
    }

    @Override
    void applyOffer(Check check) {
        check.addPoints(rewardRule.calculatePoints(check));
    }
}
