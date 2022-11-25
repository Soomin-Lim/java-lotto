package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    NOTHING(0, 0);

    private final int count;
    private final int reward;

    Rank(int count, int reward) {
        this.count = count;
        this.reward = reward;
    }

    public static Rank findRank(int count, boolean isBonus) {
        if (isBonus && count == SECOND.count) {
            return Rank.SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> rank != Rank.SECOND)
                .filter(rank -> rank.count == count)
                .findFirst()
                .orElse(NOTHING);
    }

    public int getCount() {
        return count;
    }

    public int getReward() {
        return reward;
    }
}
