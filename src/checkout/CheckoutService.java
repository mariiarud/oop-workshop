package checkout;

import checkout.offers.FactorByCategoryOffer;
import checkout.offers.Offer;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

    private Check check;
    private List<Offer> appliedOffers = new ArrayList<>();

    public void openCheck() {
        check = new Check();
    }

    public void addProduct(Product product) {
        if (check == null) {
            openCheck();
        }
        check.addProduct(product);
    }

    public Check closeCheck() {
        Check closedCheck = check;

        for (Offer offer: appliedOffers)
            useOffer(offer);

        check = null;
        return closedCheck;
    }

    public void useOffer(Offer offer) {
        offer.apply(check);
    }

    public void addOffer(FactorByCategoryOffer offer) {
        appliedOffers.add(offer);
    }

}
