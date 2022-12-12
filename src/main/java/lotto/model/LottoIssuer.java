package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.util.LottoConstUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoIssuer {

    private final LottoNumbersGenerator lottoNumbersGenerator;

    public LottoIssuer(LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbersGenerator = Objects.requireNonNull(lottoNumbersGenerator);
    }

    public List<Lotto> issue(PurchaseAmount purchaseAmount) {
        List<Lotto> lotteries = new ArrayList<>();

        BigInteger lottoCounts = purchaseAmount.getLottoCounts();
        while(lottoCounts.compareTo(BigInteger.ZERO) > 0) {
            Lotto lotto = new Lotto(lottoNumbersGenerator.generate());
            lotteries.add(lotto);
            lottoCounts = lottoCounts.subtract(BigInteger.ONE);
        }

        return lotteries;
    }
}
