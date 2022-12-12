package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.util.LottoConstUtil;

import java.util.List;

public class LottoRandomNumbersGenerator implements LottoNumbersGenerator {

    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(
                LottoConstUtil.MIN_NUMBER,
                LottoConstUtil.MAX_NUMBER,
                LottoConstUtil.NUMBER_SIZE);

    }
}
