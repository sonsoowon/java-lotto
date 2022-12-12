package lotto.model;

import lotto.util.ErrorMessageUtil;
import lotto.util.LottoConstUtil;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.lottoNumbers = numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }

    private static void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private static void validateSize(List<Integer> numbers) {
        if (!correctSize(numbers)) {
            throw new IllegalArgumentException(ErrorMessageUtil.INVALID_LOTTO_NUMBER_SIZE.fullMessage());
        }
    }

    private static boolean correctSize(List<Integer> numbers) {
        return numbers.size() == LottoConstUtil.NUMBER_SIZE;
    }

    private static void validateDuplicate(List<Integer> numbers) {
        if (!noDuplicate(numbers)) {
            throw new IllegalArgumentException(ErrorMessageUtil.DUPLICATE_LOTTO_NUMBER.fullMessage());
        }
    }

    private static boolean noDuplicate(List<Integer> numbers) {
        int distinctSize = (int) numbers.stream()
                .distinct()
                .count();
        return distinctSize == numbers.size();
    }

    public String getNumbers() {
        return lottoNumbers.stream()
                .sorted()
                .map(LottoNumber::toString)
                .collect(Collectors.toUnmodifiableList())
                .toString();
    }

    public int containCounts(Lotto other) {
        return (int) this.lottoNumbers.stream()
                .filter(other.lottoNumbers::contains)
                .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return this.lottoNumbers.contains(lottoNumber);
    }

}
