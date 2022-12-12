package lotto.model;

import lotto.util.ErrorMessageUtil;
import lotto.util.LottoConstUtil;

import java.util.Comparator;
import java.util.stream.IntStream;

public class LottoNumber implements Comparator<LottoNumber> {

    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        validate(number);
        return LottoNumberCache.cache[number - 1];
    }

    private static void validate(int number) {
        if (!correctRange(number)) {
            throw new IllegalArgumentException(ErrorMessageUtil.INVALID_LOTTO_NUMBER_RANGE.fullMessage());
        }
    }

    private static boolean correctRange(int number) {
        return LottoConstUtil.MIN_NUMBER <= number && number <= LottoConstUtil.MAX_NUMBER;
    }


    private static class LottoNumberCache {
        static final LottoNumber[] cache = new LottoNumber[LottoConstUtil.MAX_NUMBER];

        static {
            IntStream.rangeClosed(LottoConstUtil.MIN_NUMBER, LottoConstUtil.MAX_NUMBER)
                    .forEach(number -> cache[number - 1] = new LottoNumber(number));
        }
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compare(LottoNumber a, LottoNumber b) {
        return a.number - b.number;
    }
}
