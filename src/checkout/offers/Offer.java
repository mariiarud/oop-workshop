package checkout.offers;

import checkout.Check;
import checkout.condition.Condition;

import java.time.LocalDate;

public abstract class Offer{
    LocalDate expiration;
    Condition condition;

    Offer(LocalDate expiration, Condition condition){
        this.expiration = expiration;
        this.condition = condition;
    }

    public final void apply(Check check){
        if( verifyExpiration() && condition.verifyCondition(check))
            applyOffer(check);
    }

    private boolean verifyExpiration(){
        return LocalDate.now().isBefore(expiration);
    }

    abstract void applyOffer(Check check);

}
