package lotto.model;

import lotto.util.LottoConstUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoRandomNumbersGeneratorTest {
    private static final LottoNumbersGenerator lottoNumbersGenerator = new LottoRandomNumbersGenerator();
    private static final Predicate<Integer> IN_CORRECT_RANGE = number ->
            LottoConstUtil.MIN_NUMBER <= number && number <= LottoConstUtil.MAX_NUMBER;


    @DisplayName("1부터 45사이의 서로 다른 6가지 수를 생성한다")
    @Test
    void generateTest() {
        List<Integer> lottoNumbers = lottoNumbersGenerator.generate();

        assertThat(lottoNumbers)
                .hasSize(LottoConstUtil.NUMBER_SIZE)
                .doesNotHaveDuplicates()
                .allMatch(IN_CORRECT_RANGE);
    }

}
