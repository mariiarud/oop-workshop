package checkout;

public abstract class Offer {
    public final void apply(Check check){
        if(offerVerification(check))
            applyOffer(check);
    }

    protected abstract boolean offerVerification(Check check);
    protected abstract void applyOffer(Check check);
}
