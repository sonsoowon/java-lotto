package lotto.dto;

import lotto.model.Lotto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LotteriesDto {
    private final List<Lotto> lotteries;

    private LotteriesDto(List<Lotto> lotteries) {
        this.lotteries = Objects.requireNonNull(lotteries);
    }

    public static LotteriesDto from(List<Lotto> lotteries) {
        return new LotteriesDto(lotteries);
    }

    public List<Lotto> getLotteries() {
        return Collections.unmodifiableList(lotteries);
    }
}
