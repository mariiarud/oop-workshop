package checkout;

import java.util.Calendar;
import java.util.Date;

public class BonusByTrademarkOffer extends Offer {

    final String bonusType;
    final Date shelfLife;
    final Trademark trademark;

    public BonusByTrademarkOffer(String bonusType, Date shelfLife, Trademark trademark) {
        this.bonusType = bonusType;
        this.shelfLife = shelfLife;
        this.trademark = trademark;
    }

    @Override
    protected boolean offerVerification(Check check) {
        return (shelfLife.after(Calendar.getInstance().getTime()) || shelfLife.equals(Calendar.getInstance().getTime()));
    }

    @Override
    protected void applyOffer(Check check) {
        switch (bonusType){
            case "-50%":
                check.addDiscount(trademark);
                break;
            case "present":
                check.addPresent(trademark);
                break;
        }
    }
}