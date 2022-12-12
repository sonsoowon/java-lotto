package lotto.model;

import lotto.util.ErrorMessageUtil;
import lotto.util.LottoConstUtil;

import java.math.BigInteger;

public class PurchaseAmount {
    private final BigInteger amount;

    private PurchaseAmount(BigInteger amount) {
        validate(amount);
        this.amount = amount;
    }

    public static PurchaseAmount valueOf(BigInteger amount) {
        return new PurchaseAmount(amount);
    }

    private static void validate(BigInteger amount) {
        validateRange(amount);
        validateUnit(amount);
    }

    private static void validateRange(BigInteger amount) {
        if(!correctRange(amount)) {
            throw new IllegalArgumentException(ErrorMessageUtil.INVALID_AMOUNT_RANGE.fullMessage());
        }
    }

    private static boolean correctRange(BigInteger amount) {
        return amount.compareTo(BigInteger.ZERO) >= 0;
    }

    private static void validateUnit(BigInteger amount) {
        if(!correctUnit(amount)) {
            throw new IllegalArgumentException(ErrorMessageUtil.INVALID_AMOUNT_UNIT.fullMessage());
        }
    }

    private static boolean correctUnit(BigInteger amount) {
        return amount.mod(LottoConstUtil.PRICE)
                .equals(BigInteger.ZERO);
    }

    public BigInteger getLottoCounts() {
        return amount.divide(LottoConstUtil.PRICE);
    }
}
