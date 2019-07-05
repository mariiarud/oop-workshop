package checkout.reward;

import checkout.Check;

public interface Reward {
    int calculatePoints(Check check);
}