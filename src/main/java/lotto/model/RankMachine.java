package lotto.model;

import lotto.util.ErrorMessageUtil;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class RankMachine {
    private final Lotto jackpotNumbers;
    private final LottoNumber bonusNumber;

    public RankMachine(Lotto jackpotNumbers, LottoNumber bonusNumber) {
        validate(jackpotNumbers, bonusNumber);
        this.jackpotNumbers = jackpotNumbers;
        this.bonusNumber = bonusNumber;
    }

    private static void validate(Lotto jackpotNumbers, LottoNumber bonusNumber) {
        if(jackpotNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessageUtil.INVALID_BONUS_NUMBER.fullMessage());
        }
    }

    public Map<Rank, BigInteger> getResultOf(List<Lotto> lotteries) {
        Map<Rank, BigInteger> result = new EnumMap<>(Rank.class);
        Objects.requireNonNull(lotteries).stream()
                .map(this::getRankOf)
                .forEach(rank -> increaseCountOfRank(rank, result));
        return Collections.unmodifiableMap(result);
    }

    private void increaseCountOfRank(Rank rank, Map<Rank, BigInteger> result) {
        BigInteger count = result.getOrDefault(rank, BigInteger.ZERO);
        result.put(rank, count.add(BigInteger.ONE));
    }

    private Rank getRankOf(Lotto lotto) {
        int hitCount = lotto.containCounts(jackpotNumbers);
        boolean hasBonus = lotto.contains(bonusNumber);

        return Rank.getInstanceOf(hitCount, hasBonus);
    }


}
