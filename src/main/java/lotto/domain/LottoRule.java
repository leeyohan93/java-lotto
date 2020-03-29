package lotto.domain;

import java.util.Arrays;

public class LottoRule {
    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final long PRICE = 1000;
    private static final int WINNING_MIN_COUNT = 3;

    static int getLottoStartNumber() {
        return LOTTO_START_NUMBER;
    }

    static int getLottoEndNumber() {
        return LOTTO_END_NUMBER;
    }

    public static int getLottoNumbersSize() {
        return LOTTO_NUMBERS_SIZE;
    }

    public static long getPrice() {
        return PRICE;
    }

    public static int getWinningMinCount() {
        return WINNING_MIN_COUNT;
    }

    public enum Rank {
        FIRST(6, 2000000000),
        SECOND(5, 30000000),
        THIRD(5, 1500000),
        FOURTH(4, 50000),
        FIFTH(3, 5000);

        private int matchCount;
        private long amount;

        Rank(int matchCount, long amount) {
            this.matchCount = matchCount;
            this.amount = amount;
        }

        public static Rank findByLottoTicketResult(LottoTicketResult lottoTicketResult) {
            int matchCount = lottoTicketResult.getMatchCount();
            boolean bonusMatch = lottoTicketResult.isBonusMatch();

            if (matchCount == SECOND.getMatchCount() && bonusMatch) {
                return SECOND;
            }
            return Arrays.stream(values())
                    .filter(s -> bonusMatch)
                    .filter(s -> s.matchCount == matchCount)
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("당첨 되지 않았습니다."));
        }

        public long getAmount() {
            return amount;
        }

        public int getMatchCount() {
            return matchCount;
        }
    }
}
