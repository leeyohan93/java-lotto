package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.domain.LottoRule.*;

public class Buyer {
    private List<LottoTicket> lottoTickets;

    public Buyer() {
    }

    public Buyer(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public List<LottoTicket> buyLottoTickets(long money) {
        lottoTickets = LottoMachine.pay(money);
        return lottoTickets;
    }

    public BuyerResult getResult(LottoTicket winningTicket, LottoNumber bonusNumber) {
        List<Rank> winningValues = getWinningValues(winningTicket, bonusNumber);
        return new BuyerResult(winningValues, getProfitRate(winningValues));
    }

    private List<Rank> getWinningValues(LottoTicket winningTicket, LottoNumber bonusNumber) {
        List<LottoTicketResult> winningLottoTicketResults = lottoTickets.stream()
                .map(lottoTicket -> lottoTicket.checkWinning(winningTicket, bonusNumber))
                .filter(result -> result.getMatchCount() >= getWinningMinCount())
                .collect(Collectors.toList());

        return winningLottoTicketResults.stream()
                .map(Rank::findByLottoTicketResult)
                .collect(Collectors.toList());
    }

    private double getProfitRate(List<Rank> winningValues) {
        long winningAmountSum = winningValues.stream()
                .mapToLong(Rank::getAmount)
                .sum();
        double profitRate = ((double) winningAmountSum) / (lottoTickets.size() * getPrice());
        return Math.round(profitRate * 100) / 100.0;
    }
}
