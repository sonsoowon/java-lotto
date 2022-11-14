package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class TotalResult {

    private static final int RANK_COUNT_DEFAULT_VALUE = 0;
    private static final String RANK_COUNT_MESSAGE_FORMAT = " - %d개";
    private final JackpotBonus jackpotBonus;
    private final List<Lotto> lotteries;

    public TotalResult(JackpotBonus jackpotBonus, List<Lotto> lotteries) {
        this.jackpotBonus = jackpotBonus;
        this.lotteries = lotteries;
    }

    public Map<Rank, Integer> getRankCounts() {
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);

        for (Lotto lotto : lotteries) {
            Rank rankOfLotto = jackpotBonus.getRankOf(lotto);
            int currentCnt = rankCounts.getOrDefault(rankOfLotto, RANK_COUNT_DEFAULT_VALUE);
            rankCounts.put(rankOfLotto, currentCnt + 1);
        }
        return rankCounts;
    }

    public List<String> getRankInfoWithCounts(Map<Rank, Integer> rankCounts) {
        List<String> result = new ArrayList<>();

        List<Rank> reversedRanks = getReversedRanks();
        for (Rank rank : reversedRanks) {
            int count = rankCounts.getOrDefault(rank, RANK_COUNT_DEFAULT_VALUE);
            String rankInfoWithCount = rank.getInfo() + String.format(RANK_COUNT_MESSAGE_FORMAT, count);
            result.add(rankInfoWithCount);
        }
        return result;
    }

    private static List<Rank> getReversedRanks() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NO_LUCK)
                .sorted(Comparator.comparing(Rank::getRankNumber).reversed())
                .collect(Collectors.toList());
    }
}
