package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.math.BigInteger;

import static org.assertj.core.api.Assertions.*;

public class RankTest {
    @DisplayName("일치하는 당첨 번호의 개수와 보너스 번호로 순위를 산출할 때")
    @Nested
    class GetInstanceOfTest {
        @DisplayName("당첨 번호의 개수가 유효하면 순위 객체를 반환한다")
        @ParameterizedTest
        @MethodSource("lotto.model.source.RankTest#getInstanceOfTestSource")
        void getInstanceOfTest(int hitCount, boolean hasBonus, Rank expected) {
            Rank actual = Rank.getInstanceOf(hitCount, hasBonus);

            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("잘못된 당첨 번호의 개수가 주어지면 예외가 발생한다")
        @Test
        void invalid() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Rank.getInstanceOf(7, false));
        }
    }

    @DisplayName("당첨된 개수만큼 상금의 합계를 구할 때")
    @Nested
    class TotalRewardTest {

        @DisplayName("유효한 개수가 주어지면 합계를 반환한다")
        @ParameterizedTest
        @MethodSource("lotto.model.source.RankTest#totalRewardTestSource")
        void valid(Rank rank, BigInteger expected) {
            BigInteger validCount = BigInteger.ONE;
            BigInteger actual = rank.totalReward(validCount);

            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("음수가 주어지면 예외가 발생한다")
        @Test
        void invalid() {
            Rank rank = Rank.FIFTH;
            BigInteger invalidCount = BigInteger.ONE.negate();
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> rank.totalReward(invalidCount));
        }
    }

    @DisplayName("순위별 상금 정보와 당첨 개수를 문자열로 반환할 때")
    @Nested
    class FullMessageTest {

        BigInteger count = BigInteger.ONE;

        @DisplayName("상금이 있는 순위가 주어지면 문자열을 반환한다")
        @ParameterizedTest
        @MethodSource("lotto.model.source.RankTest#fullMessageTestSource")
        void valid(Rank rank, String expected) {
            String actual = rank.fullMessage(count);

            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("상금이 없는 순위가 주어지면 예외가 발생한다")
        @Test
        void invalid() {
            Rank invalidRank = Rank.NO_LUCK;
            assertThatIllegalStateException()
                    .isThrownBy(() -> invalidRank.fullMessage(count));
        }
    }

}
