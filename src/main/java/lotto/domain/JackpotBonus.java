package lotto.domain;

import lotto.valid.LottoValidator;

import java.util.List;

public class JackpotBonus {
    private final List<Integer> jackpotNumbers;
    private final int bonusNumber;
    private static final String BONUS_NUMBER_DUPLICATE_ERROR_MESSAGE = "[ERROR] 보너스 번호는 당첨 번호와 중복되지 않아야 합니다.";

    public JackpotBonus(List<Integer> jackpotNumbers, int bonusNumber) {
        this.jackpotNumbers = jackpotNumbers;

        checkDuplicateBonus(jackpotNumbers, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private static void checkDuplicateBonus(List<Integer> jackpotNumbers, int bonusNumber) {
        if (jackpotNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_ERROR_MESSAGE);
        }
    }
}
