package checkout;

import java.util.Calendar;
import java.util.Date;

public class BonusByProductOffer extends Offer {

    final String bonusType;
    final Date shelfLife;
    final Product product;

    public BonusByProductOffer(String bonusType, Date shelfLife, Product product) {
        this.bonusType = bonusType;
        this.shelfLife = shelfLife;
        this.product = product;
    }

    @Override
    protected boolean offerVerification(Check check) {
        return (shelfLife.after(Calendar.getInstance().getTime()) || shelfLife.equals(Calendar.getInstance().getTime()));
    }

    @Override
    protected void applyOffer(Check check) {
        switch (bonusType){
            case "-50%":
                check.addDiscount(product);
                break;
            case "present":
                check.addPresent(product);
                break;
        }
    }


}
