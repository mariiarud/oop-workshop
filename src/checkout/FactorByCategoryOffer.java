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
    public void apply(Check check) {
        int points = check.getCostByCategory(category);
        if(shelfLife.after(Calendar.getInstance().getTime()) || shelfLife.equals(Calendar.getInstance().getTime()))
            check.addPoints(points * (factor - 1));
    }
}
