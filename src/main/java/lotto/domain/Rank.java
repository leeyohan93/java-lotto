package lotto.domain;

import java.util.Arrays;

public enum Rank {
    MISS(0, Money.ZERO()),
    FIFTH(3, Money.of(5_000)),
    FOURTH(4, Money.of(50_000)),
    THIRD(5, Money.of(1_500_000)),
    SECOND(5, Money.of(30_000_000)),
    FIRST(6, Money.of(2_000_000_000));

    private int matchCount;
    private Money reward;

    Rank(int matchCount, Money reward) {
        this.matchCount = matchCount;
        this.reward = reward;
    }

    public static Rank of(WiningResult winingResult) {
        int matchCount = winingResult.getMatchCount();
        boolean bonusMatch = winingResult.isBonusMatch();

        if (matchCount == SECOND.getMatchCount() && bonusMatch) {
            return SECOND;
        }
        return Arrays.stream(values())
                .filter(s -> s.matchCount == matchCount)
                .findFirst()
                .orElse(MISS);
    }

    public Money getReward() {
        return reward;
    }

    public int getMatchCount() {
        return matchCount;
    }
    }
