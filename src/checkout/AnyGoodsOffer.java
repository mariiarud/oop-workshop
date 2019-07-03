package checkout;

public class AnyGoodsOffer extends Offer {
    public final int totalCost;
    public final int points;

    public AnyGoodsOffer(int totalCost, int points) {
        this.totalCost = totalCost;
        this.points = points;
    }

    @Override
    protected boolean offerVerification(Check check) {
        return (totalCost <= check.getTotalCost());
    }

    @Override
    protected void applyOffer(Check check) {
        check.addPoints(points);
    }
}
