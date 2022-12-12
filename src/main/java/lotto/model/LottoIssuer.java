package lotto.model;

import lotto.dto.LotteriesDto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoIssuer {
    private final LottoNumbersGenerator lottoNumbersGenerator;

    public LottoIssuer(LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbersGenerator = Objects.requireNonNull(lottoNumbersGenerator);
    }

    public LotteriesDto issue(PurchaseAmount purchaseAmount) {
        BigInteger count = Objects.requireNonNull(purchaseAmount).getLottoCounts();
        List<Lotto> lotteries = issueLotteriesByCount(count);

        return LotteriesDto.from(lotteries);
    }

    private List<Lotto> issueLotteriesByCount(BigInteger count) {
        List<Lotto> lotteries = new ArrayList<>();
        while (count.compareTo(BigInteger.ZERO) > 0) {
            Lotto lotto = new Lotto(lottoNumbersGenerator.generate());
            lotteries.add(lotto);
            count = count.subtract(BigInteger.ONE);
        }

        return lotteries;
    }
}
