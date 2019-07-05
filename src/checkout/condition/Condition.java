package checkout.condition;

import checkout.Check;

public interface Condition {
    boolean verifyCondition(Check check);
}
