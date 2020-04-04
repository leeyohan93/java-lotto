package lotto.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketForm {
    private final List<Integer> lottoNumbers;

    public static LottoTicketForm of(int... lottoNumbers) {
        return new LottoTicketForm(Arrays.stream(lottoNumbers)
                .boxed()
                .collect(Collectors.toList()));
    }

    private LottoTicketForm(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
