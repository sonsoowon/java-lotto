package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("Lotto 생성자를 호출했을 때")
    @Nested
    class ConstructorTest {
        @DisplayName("로또 번호의 개수가 6개보다 적으면 예외가 발생한다")
        @Test
        void invalidSize() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)));
        }

        @DisplayName("1보다 작거나 45보다 큰 숫자가 있으면 예외가 발생한다")
        @Nested
        class InvalidRange {
            @ParameterizedTest
            @ValueSource(ints = {0, 46})
            void invalidRange(int invalidNumber) {
                assertThatIllegalArgumentException()
                        .isThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, invalidNumber)));
            }
        }

        @DisplayName("1부터 45사이의 서로 다른 6가지 로또 번호가 있으면 Lotto객체를 반환한다")
        @Test
        void valid() {
            assertThatNoException()
                    .isThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        }
    }

    @DisplayName("오름차순 정렬된 로또 번호를 문자열로 반환한다")
    @Test
    void getNumbersTest() {
        Lotto lotto = new Lotto(List.of(6, 5, 4, 2, 3, 1));
        String expected = "[1, 2, 3, 4, 5, 6]";

        String actual = lotto.getNumbers();

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 로또 객체와 동일한 번호의 개수를 반환한다")
    @ParameterizedTest
    @MethodSource("containCountsTestSource")
    void containCountsTest(Lotto other, int expected) {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        int actual = lotto.containCounts(other);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("주어진 로또 번호를")
    @Nested
    class ContainsTest {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        @DisplayName("포함하면 참을 반환한다")
        @Test
        void returnTrue() {
            LottoNumber lottoNumber = LottoNumber.valueOf(1);
            boolean actual = lotto.contains(lottoNumber);

            assertThat(actual).isTrue();
        }

        @DisplayName("포함하지 않으면 거짓을 반환한다")
        @Test
        void returnFalse() {
            LottoNumber lottoNumber = LottoNumber.valueOf(7);
            boolean actual = lotto.contains(lottoNumber);

            assertThat(actual).isFalse();
        }
    }

    private static Stream<Arguments> containCountsTestSource() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 6),
                Arguments.of(new Lotto(List.of(2, 3, 4, 5, 6, 7)), 5),
                Arguments.of(new Lotto(List.of(3, 4, 5, 6, 7, 8)), 4),
                Arguments.of(new Lotto(List.of(4, 5, 6, 7, 8, 9)), 3),
                Arguments.of(new Lotto(List.of(5, 6, 7, 8, 9, 10)), 1),
                Arguments.of(new Lotto(List.of(7, 8, 9, 10, 11, 12)), 0)
        );
    }
}
