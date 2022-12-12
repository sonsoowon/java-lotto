package lotto.model;

import lotto.util.ErrorMessageUtil;
import lotto.util.LottoConstUtil;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.function.Function;

public enum Rank {
    FIRST(BigInteger.valueOf(2_000_000_000), 6, bonus -> true),
    SECOND(BigInteger.valueOf(30_000_000), 5, bonus -> bonus),
    THIRD(BigInteger.valueOf(1_500_000), 5, bonus -> !bonus),
    FOURTH(BigInteger.valueOf(50_000), 4, bonus -> true),
    FIFTH(BigInteger.valueOf(5000), 3, bonus -> true),
    NO_LUCK(BigInteger.ZERO, 0, bonus -> true);

    private final BigInteger reward;
    private final int hitCount;
    private final Function<Boolean, Boolean> hasBonus;

    Rank(BigInteger reward, int hitCount, Function<Boolean, Boolean> hasBonus) {
        this.reward = reward;
        this.hitCount = hitCount;
        this.hasBonus = hasBonus;
    }

    public static Rank getInstanceOf(int lottoHitCount, boolean lottoHasBonus) {
        validateHitCount(lottoHitCount);
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.hitCount == lottoHitCount)
                .filter(rank -> rank.hasBonus.apply(lottoHasBonus))
                .findFirst()
                .orElse(Rank.NO_LUCK);
    }

    private static void validateHitCount(int hitCount) {
        if(!correctRange(hitCount)) {
            throw new IllegalArgumentException(ErrorMessageUtil.INVALID_HIT_COUNT.fullMessage());
        }
    }

    private static boolean correctRange(int hitCount) {
        return 0 <= hitCount && hitCount <= LottoConstUtil.NUMBER_SIZE;
    }

    public BigInteger totalReward(BigInteger count) {
        validateCount(count);
        return reward.multiply(count);
    }

    public String fullMessage(BigInteger count) {
        validateCount(count);
        if(this == Rank.NO_LUCK) {
            throw new IllegalStateException(ErrorMessageUtil.INVALID_RANK_MESSAGE.fullMessage());
        }

        String baseMessage = Message.baseMessage(this);
        return String.format(baseMessage, count);
    }

    private static void validateCount(BigInteger count) {
        if(count.compareTo(BigInteger.ZERO) >= 0) {
            throw new IllegalArgumentException(ErrorMessageUtil.INVALID_COUNT.fullMessage());
        }
    }

    private static class Message {

        private static final String NORMAL_FORMAT = "%d개 일치 (%s원) - %s개";
        private static final String BONUS_FORMAT = "%d개 일치, 보너스 볼 일치 (%s원) - %s개";
        private static final NumberFormat REWARD_FORMATTER = new DecimalFormat("###,###");

        static String baseMessage(Rank rank) {
            String reward = REWARD_FORMATTER.format(rank.reward);

            String message = NORMAL_FORMAT;
            if(rank == Rank.SECOND) {
                message = BONUS_FORMAT;
            }

            return String.format(message, rank.hitCount, reward);
        }
    }
}
