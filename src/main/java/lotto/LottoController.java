package lotto;

import lotto.domain.Buyer;
import lotto.domain.BuyerResult;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    public static void main(String[] args) {
        long price = InputView.inputPrice();

        Buyer buyer = new Buyer();
        List<LottoTicket> lottoTickets = buyer.buyLottoTickets(price);

        ResultView.printLottoTickets(lottoTickets);
        int[] winningNumbers = InputView.inputWinningNumbers();

        BuyerResult buyerResult = buyer.getResult(createLottoTicket(winningNumbers));

        ResultView.printWinningStatistics(buyerResult);
    }

    private static LottoTicket createLottoTicket(int[] winningNumbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int winningNumber : winningNumbers) {
            lottoNumbers.add(LottoNumber.of(winningNumber));
        }
        return new LottoTicket(lottoNumbers);
    }
}
