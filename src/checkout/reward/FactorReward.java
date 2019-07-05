package checkout.reward;

import checkout.Category;
import checkout.Check;

public class FactorReward implements Reward {
    final Category category;
    final int factor;

    public FactorReward(Category category, int factor){
        this.category = category;
        this.factor = factor;
    }

    @Override
    public int calculatePoints(Check check) {
        int points = check.getTotalCostByCondition(p -> p.category == category);
        return points*(factor - 1);
    }
}
