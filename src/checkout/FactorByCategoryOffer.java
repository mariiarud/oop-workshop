package checkout;

import java.util.Calendar;
import java.util.Date;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;
    final Date shelfLife;

    public FactorByCategoryOffer(Category category, int factor, Date shelfLife) {
        this.category = category;
        this.factor = factor;
        this.shelfLife = shelfLife;
    }

    @Override
    protected boolean offerVerification(Check check) {
        return (shelfLife.after(Calendar.getInstance().getTime()) || shelfLife.equals(Calendar.getInstance().getTime()));
    }

    @Override
    protected void applyOffer(Check check) {
        int points = check.getCostByCategory(category);
        check.addPoints(points * (factor - 1));
    }
}
