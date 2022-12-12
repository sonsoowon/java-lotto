package lotto.model.source;

import lotto.model.Rank;
import org.junit.jupiter.params.provider.Arguments;

import java.math.BigInteger;
import java.util.stream.Stream;

public final class RankTest {

    private RankTest() {
    }

    static Stream<Arguments> getInstanceOfTestSource() {
        return Stream.of(
                Arguments.of(6, true, Rank.FIRST),
                Arguments.of(6, false, Rank.FIRST),
                Arguments.of(5, true, Rank.SECOND),
                Arguments.of(5, false, Rank.THIRD),
                Arguments.of(4, true, Rank.FOURTH),
                Arguments.of(4, false, Rank.FOURTH),
                Arguments.of(3, true, Rank.FIFTH),
                Arguments.of(3, false, Rank.FIFTH),
                Arguments.of(2, true, Rank.NO_LUCK)
        );
    }

    static Stream<Arguments> totalRewardTestSource() {
        return Stream.of(
                Arguments.of(Rank.FIRST, BigInteger.valueOf(2_000_000_000)),
                Arguments.of(Rank.SECOND, BigInteger.valueOf(30_000_000)),
                Arguments.of(Rank.THIRD, BigInteger.valueOf(1_500_000)),
                Arguments.of(Rank.FOURTH, BigInteger.valueOf(50_000)),
                Arguments.of(Rank.FIFTH, BigInteger.valueOf(5000)),
                Arguments.of(Rank.NO_LUCK, BigInteger.ZERO)
        );
    }

    static Stream<Arguments> fullMessageTestSource() {
        return Stream.of(
                Arguments.of(Rank.FIRST, "6개 일치 (2,000,000,000원) - 1개"),
                Arguments.of(Rank.SECOND, "5개 일치, 보너스 볼 일치 (30,000,000원) - 1개"),
                Arguments.of(Rank.THIRD, "5개 일치 (1,500,000원) - 1개"),
                Arguments.of(Rank.FOURTH, "4개 일치 (50,000원) - 1개"),
                Arguments.of(Rank.FIFTH, "3개 일치 (5,000원) - 1개")
        );
    }
}
