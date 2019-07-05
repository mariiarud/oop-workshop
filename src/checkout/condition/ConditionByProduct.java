package checkout.condition;

import checkout.Check;
import checkout.Product;

public class ConditionByProduct implements Condition {
    final Product product;

    public ConditionByProduct(Product product){
        this.product = product;
    }

    @Override
    public boolean verifyCondition(Check check) {
        return check.verifyIfProductIsInCheck(p -> p == product);
    }
}
