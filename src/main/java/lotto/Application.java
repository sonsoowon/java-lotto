package lotto;

import lotto.domain.*;
import lotto.ui.*;
import lotto.valid.InputValidator;

import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        try {
            List<Lotto> lotteries = purchaseLotteries();
            JackpotBonus jackpotBonus = pickUpJackpotBonusBall();
            printTotalResult(jackpotBonus, lotteries);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Lotto> purchaseLotteries() throws IllegalArgumentException {
        String moneyInput = InputView.inputMoney();
        int money = InputValidator.getValidMoney(moneyInput);

        List<Lotto> lotteries = LottoIssuer.issue(money);
        OutputView.printLottoReceipts(lotteries);

        return lotteries;
    }

    private static JackpotBonus pickUpJackpotBonusBall() throws IllegalArgumentException {
        String jackpotInput = InputView.inputJackpotNumbers();
        List<Integer> jackpotNumbers = InputValidator.getValidJackpotNumbers(jackpotInput);

        String bonusInput = InputView.inputBonusNumber();
        int bonusNumber = InputValidator.getValidBonusNumber(bonusInput);

        return new JackpotBonus(jackpotNumbers, bonusNumber);
    }

    private static void printTotalResult(JackpotBonus jackpotBonus, List<Lotto> lotteries) {
        TotalResult totalResult = new TotalResult(jackpotBonus, lotteries);

        Map<Rank, Integer> rankCounts = totalResult.getRankCounts();
        List<String> rankInfoWithCounts = totalResult.getRankInfoWithCounts(rankCounts);
        String yield = totalResult.getYield(rankCounts);

        OutputView.printTotalResult(rankInfoWithCounts, yield);
    }
}
