package lotto.valid;

import lotto.domain.LottoMachine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum LottoValidator {
    INSTANCE;
    private static final int LOTTO_SIZE = 6;

    private static final String LOTTO_SIZE_ERROR_MESSAGE = "[ERROR] 로또 번호의 개수는 6개여야 합니다.";

    public static void validate(List<Integer> numbers) {
        checkSize(numbers);
    }

    private static void checkSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_ERROR_MESSAGE);
        }
    }
}
