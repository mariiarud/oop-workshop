package checkout.condition;

import checkout.Category;
import checkout.Check;

public class ConditionByCategory implements Condition {
    final Category category;

    public ConditionByCategory(Category category){
        this.category = category;
    }

    @Override
    public boolean verifyCondition(Check check) {
        return check.verifyIfProductIsInCheck(p -> p.category == category);
    }
}
