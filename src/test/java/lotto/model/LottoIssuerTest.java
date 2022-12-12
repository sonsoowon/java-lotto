package lotto.model;

import lotto.dto.LotteriesDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.*;

public class LottoIssuerTest {
    @DisplayName("LottoIssuer 생성자를 호출할 때")
    @Nested
    class ConstructorTest {
        @DisplayName("null을 전달하면 예외가 발생한다")
        @Test
        void invalid() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new LottoIssuer(null));
        }

        @DisplayName("null이 아닌 LottoNumbersGenerator 객체를 전달하면 예외가 발생하지 않는다")
        @Test
        void valid() {
            LottoNumbersGenerator lottoNumbersGenerator = new LottoRandomNumbersGenerator();
            assertThatNoException()
                    .isThrownBy(() -> new LottoIssuer(lottoNumbersGenerator));
        }
    }

    @DisplayName("로또를 발행할 때")
    @Nested
    class IssueTest {
        private final LottoNumbersGenerator lottoNumbersGenerator = new LottoRandomNumbersGenerator();
        private final LottoIssuer lottoIssuer = new LottoIssuer(lottoNumbersGenerator);

        @DisplayName("유효한 구입 금액을 전달하면 로또 컬렉션 객체를 반환한다")
        @ParameterizedTest
        @CsvSource(
                value = {"8000:8", "0:0"},
                delimiter = ':'
        )
        void valid(int amount, int expected) {
            PurchaseAmount purchaseAmount = PurchaseAmount.valueOf(BigInteger.valueOf(amount));

            LotteriesDto lotteriesDto = lottoIssuer.issue(purchaseAmount);
            int actual = lotteriesDto.getLotteries().size();

            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("null을 전달하면 예외가 발생한다")
        @Test
        void invalid() {
            assertThatNullPointerException()
                    .isThrownBy(() -> lottoIssuer.issue(null));
        }
    }
}
