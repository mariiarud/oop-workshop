package checkout.condition;

import checkout.Check;

public class ConditionTotalCost implements Condition {

    final int totalCost;
    public ConditionTotalCost(int totalCost){
        this.totalCost = totalCost;
    }

    @Override
    public boolean verifyCondition(Check check) {
        return (totalCost <= check.getTotalCost());
    }
}
