package lotto.model;

import lotto.util.LottoConstUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

public class LottoNumberTest {

    @DisplayName("valueOf 메서드를 호출할 때 주어진 입력이")
    @Nested
    class ValueOfTest {
        @DisplayName("1부터 45사이의 숫자가 아니면 예외가 발생한다")
        @Nested
        class Invalid {
            @ParameterizedTest
            @ValueSource(ints = {0, 46})
            void invalid(int number) {
                assertThatIllegalArgumentException()
                        .isThrownBy(() -> LottoNumber.valueOf(number));
            }
        }

        @DisplayName("1부터 45사이의 숫자이면 캐싱된 LottoNumber 객체를 반환한다")
        @Test
        void valid() {
            IntStream.rangeClosed(LottoConstUtil.MIN_NUMBER, LottoConstUtil.MAX_NUMBER)
                    .forEach(number ->
                            assertThatNoException()
                                    .isThrownBy(() -> LottoNumber.valueOf(number)));
        }
    }

    @DisplayName("toString 메서드를 호출하면 로또 번호를 문자열로 반환한다")
    @Test
    void toStringTest() {
        List<String> expected = IntStream.rangeClosed(LottoConstUtil.MIN_NUMBER, LottoConstUtil.MAX_NUMBER)
                .mapToObj(String::valueOf)
                .collect(Collectors.toUnmodifiableList());

        List<String> actual = IntStream.rangeClosed(LottoConstUtil.MIN_NUMBER, LottoConstUtil.MAX_NUMBER)
                .mapToObj(LottoNumber::valueOf)
                .map(LottoNumber::toString)
                .collect(Collectors.toUnmodifiableList());

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("두 객체 A, B를 비교할 때")
    @Nested
    class CompareTest {
        LottoNumber a = LottoNumber.valueOf(5);

        @DisplayName("A.number > B.number 이면 양수를 반환한다")
        @Test
        void aBiggerThanB() {
            LottoNumber b = LottoNumber.valueOf(1);
            int actual = a.compare(a, b);

            assertThat(actual).isGreaterThan(0);
        }

        @DisplayName("A.number == B.number 이면 0을 반환한다")
        @Test
        void aEqualToB() {
            LottoNumber b = LottoNumber.valueOf(5);
            int actual = a.compare(a, b);

            assertThat(actual).isEqualTo(0);
        }

        @DisplayName("A.number < B.number 이면 음수를 반환한다")
        @Test
        void aSmallerThanB() {
            LottoNumber b = LottoNumber.valueOf(10);
            int actual = a.compare(a, b);

            assertThat(actual).isLessThan(0);
        }
    }
}
