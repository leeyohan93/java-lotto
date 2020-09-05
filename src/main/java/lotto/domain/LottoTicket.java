package lotto.domain;

import lotto.dto.LottoTicketForm;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class LottoTicket {
    static final Money TICKET_PRICE = Money.of(1000);

    private final LottoNumbers lottoNumbers;
    private final boolean auto;

    static LottoTicket of(int... lottoNumbers) {
        return new LottoTicket(Arrays.stream(lottoNumbers)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList()));
    }

    static LottoTicket ofForm(LottoTicketForm lottoTicketForm) {
        List<Integer> lottoNumbers = lottoTicketForm.getLottoNumbers();
        return new LottoTicket(LottoNumbers.of(lottoNumbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList())), false);
    }

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        this(LottoNumbers.of(lottoNumbers), true);
    }

    private LottoTicket(LottoNumbers lottoNumbers, boolean auto) {
        this.lottoNumbers = lottoNumbers;
        this.auto = auto;
    }

    Rank checkWinning(WinningLotto winningLotto) {
        return Rank.of(winningLotto.match(lottoNumbers));
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.getLottoNumbers();
    }

    public boolean isAuto() {
        return auto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoTicket)) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
