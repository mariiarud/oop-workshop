package checkout.reward;

import checkout.Check;

public class FlatReward implements Reward {
    final int points;

    public FlatReward(int points){
        this.points = points;
    }

    @Override
    public int calculatePoints(Check check) {
        return  points;
    }
}
