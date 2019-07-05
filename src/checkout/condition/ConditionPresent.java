package checkout.condition;

import checkout.Check;
import checkout.Trademark;

public class ConditionPresent implements Condition {
    final Trademark trademark;

    public ConditionPresent(Trademark trademark){
        this.trademark = trademark;
    }

    @Override
    public boolean verifyCondition(Check check) {
        return check.verifyIfPresentIsAvailable(trademark);

    }
}
