package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.*;

public class PurchaseAmountTest {

    @DisplayName("PurchaseAmount 생성자를 호출할 때")
    @Nested
    class ConstructorTest {

        @DisplayName("유효하지 않은 정수를 전달하면 예외가 발생한다")
        @Nested
        class Invalid {
            @DisplayName("음수를 전달했을 때")
            @Test
            void incorrectRange() {
                invalid(-1000);
            }

            @DisplayName("1000원으로 나누어 떨어지지 않는 수를 전달했을 때")
            @Test
            void incorrectUnit() {
                invalid(1500);
            }

            private void invalid(int amount) {
                BigInteger invalid = BigInteger.valueOf(amount);
                assertThatIllegalArgumentException()
                        .isThrownBy(() -> PurchaseAmount.valueOf(invalid));
            }
        }


        @DisplayName("유효한 숫자를 전달하면 예외 없이 객체를 생성한다")
        @Nested
        class Valid {
            @ParameterizedTest(name = "{0}원")
            @ValueSource(ints = {0, 2000})
            void correct(int amount) {
                BigInteger valid = BigInteger.valueOf(amount);
                assertThatNoException()
                        .isThrownBy(() -> PurchaseAmount.valueOf(valid));
            }
        }
    }

    @DisplayName("구매한 로또 수량을 계산한다")
    @Test
    void getLottoCountsTest() {
        BigInteger amount = BigInteger.valueOf(8000);
        BigInteger expected = BigInteger.valueOf(8);

        BigInteger actual = PurchaseAmount.valueOf(amount).getLottoCounts();

        assertThat(actual).isEqualTo(expected);
    }
}
