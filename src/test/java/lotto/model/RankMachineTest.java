package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class RankMachineTest {
    @DisplayName("RankMachine 생성자를 호출할 때")
    @Nested
    class ConstructorTest {
        Lotto jackpotNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        @DisplayName("유효한 당첨 번호와 보너스 번호를 전달하면 예외가 발생하지 않는다")
        @Test
        void valid() {
            LottoNumber bonusNumber = LottoNumber.valueOf(7);

            assertThatNoException()
                    .isThrownBy(() -> new RankMachine(jackpotNumbers, bonusNumber));
        }

        @DisplayName("당첨 번호와 중복된 보너스 번호를 전달하면 예외가 발생한다")
        @Test
        void invalid() {
            LottoNumber bonusNumber = LottoNumber.valueOf(6);

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new RankMachine(jackpotNumbers, bonusNumber));
        }
    }

    @DisplayName("순위별 당첨된 로또 개수를 계산할때")
    @Nested
    class GetResultOfTest {
        Lotto jackpotNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        RankMachine rankMachine = new RankMachine(jackpotNumbers, bonusNumber);

        @DisplayName("유효한 로또 리스트를 전달하면 결과를 반환한다")
        @ParameterizedTest
        @MethodSource("lotto.model.source.RankMachineTest#getResultOfTestSource")
        void valid(List<Lotto> lotteries, Map<Rank, BigInteger> expected) {
            Map<Rank, BigInteger> actual = rankMachine.getResultOf(lotteries);

            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("null을 전달하면 예외가 발생한다")
        @Test
        void invalid() {
            assertThatNullPointerException()
                    .isThrownBy(() -> rankMachine.getResultOf(null));
        }
    }
}
