package checkout.condition;

import checkout.Check;
import checkout.Trademark;

public class ConditionByTrademark implements Condition {
    final Trademark trademark;

    public ConditionByTrademark(Trademark trademark){
        this.trademark = trademark;
    }

    @Override
    public boolean verifyCondition(Check check) {
        return check.verifyIfProductIsInCheck(p -> p.trademark == trademark);
    }
}
