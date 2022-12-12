package lotto.model.source;

import lotto.model.Lotto;
import lotto.model.Rank;
import org.junit.jupiter.params.provider.Arguments;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public final class RankMachineTest {
    static Stream<Arguments> getResultOfTestSource() {
        return Stream.of(
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                                new Lotto(List.of(2, 3, 4, 5, 6, 7)),
                                new Lotto(List.of(1, 2, 3, 4, 5, 8))),
                        Map.of(Rank.FIRST, BigInteger.ONE,
                                Rank.SECOND, BigInteger.ONE,
                                Rank.THIRD, BigInteger.ONE)
                ),
                Arguments.of(
                        List.of(), Map.of()
                )
        );
    }
}
