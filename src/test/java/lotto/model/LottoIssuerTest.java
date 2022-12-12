package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;
import java.util.List;

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

    @DisplayName("구매 금액만큼의 로또를 발행한다")
    @Nested
    class IssueTest {
        private final LottoNumbersGenerator lottoNumbersGenerator = new LottoRandomNumbersGenerator();
        private final LottoIssuer lottoIssuer = new LottoIssuer(lottoNumbersGenerator);

        @ParameterizedTest
        @CsvSource(
                value = {"8000:8", "0:0"},
                delimiter = ':'
        )
        void issueLotteriesWithPurchaseAmount(int amount, int expected) {
            PurchaseAmount purchaseAmount = PurchaseAmount.valueOf(BigInteger.valueOf(amount));

            List<Lotto> lotteries = lottoIssuer.issue(purchaseAmount);
            int actual = lotteries.size();

            assertThat(actual).isEqualTo(expected);
        }
    }
}
