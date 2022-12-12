package lotto.util;

public enum ErrorMessageUtil {
    INVALID_AMOUNT_RANGE("금액은 0원 이상이어야 합니다."),
    INVALID_AMOUNT_UNIT("금액은 %d원 단위여야 합니다", LottoConstUtil.PRICE),

    INVALID_LOTTO_NUMBER_RANGE("%d에서 %d사이의 숫자여야 합니다.", LottoConstUtil.MIN_NUMBER, LottoConstUtil.MAX_NUMBER),
    INVALID_LOTTO_NUMBER_COUNT("%d개의 숫자여야 합니다.", LottoConstUtil.NUMBER_SIZE),
    DUPLICATE_LOTTO_NUMBER("숫자는 중복되지 않아야 합니다."),

    INVALID_BONUS_NUMBER("보너스 번호는 당첨 번호와 중복되지 않아야 합니다.");


    private static final String PREDICATE = "[ERROR] ";
    private final String message;

    ErrorMessageUtil(String message, Object... number) {
        this.message = String.format(message, number);
    }

    public String fullMessage() {
        return PREDICATE + message;
    }
}
