package lotto.view;

import lotto.domain.BuyerResult;
import lotto.domain.LottoRule.Rank;
import lotto.domain.LottoTicket;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ResultView {
    private static final String ENTER = "\n";
    private static final String BUY_COUNT_FORMAT = "%d개를 구매했습니다.";
    private static final String LOTTO_TICKET_FORMAT = "[%d, %d, %d, %d, %d, %d]";
    private static final String WINNING_STATISTICS_TITLE = "당첨 통계\n---------";
    private static final String WINNING_STATISTICS_FORMAT = "%d개 일치 (%d원)- %d개";
    private static final String WINNING_STATISTICS_PROFIT_RATE_FORMAT = "총 수익률은 %.2f 입니다.";
    private static final String RESULT_GOOD = "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
    private static final String RESULT_SOSO = "(기준이 1이기 때문에 결과적으로 쌤쌤이라는 의미임)";
    private static final String RESULT_BAD = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";


    private Scanner scanner = new Scanner(System.in);

    public void printLottoTickets(List<LottoTicket> lottoTickets) {
        System.out.println(String.format(BUY_COUNT_FORMAT, lottoTickets.size()));
        lottoTickets.forEach(lottoTicket ->
                System.out.println(String.format(LOTTO_TICKET_FORMAT, lottoTicket.getLottoNumbers().toArray())));
    }

    public void printWinningStatistics(BuyerResult buyerResult) {
        System.out.println(WINNING_STATISTICS_TITLE);
        List<Rank> winningResult = buyerResult.getWinningResult();
        Arrays.stream(Rank.values())
                .forEach(winningValue -> printWinningStatistics(winningResult, winningValue));

        double profitRate = buyerResult.getProfitRate();
        System.out.print(String.format(WINNING_STATISTICS_PROFIT_RATE_FORMAT, profitRate));
        printProfitRateDescription(profitRate);
    }

    private void printWinningStatistics(List<Rank> winningResult, Rank winningValue) {
        long count = winningResult.stream()
                .filter(value -> value == winningValue)
                .count();
        System.out.println(String.format(WINNING_STATISTICS_FORMAT,
                winningValue.getMatchCount(),
                winningValue.getAmount(),
                count));
    }

    private void printProfitRateDescription(double profitRate) {
        if (profitRate > 1) {
            System.out.print(RESULT_GOOD);
        }
        if (profitRate == 1) {
            System.out.print(RESULT_SOSO);
        }
        if (profitRate < 1) {
            System.out.print(RESULT_BAD);
        }
    }
}
