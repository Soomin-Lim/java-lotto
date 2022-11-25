package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoGameResult {

    private final Map<Rank, Integer> winningResult;

    public LottoGameResult(PlayerLotto playerLotto, WinningLotto winningLotto) {
        this.winningResult = playerLotto.createWinningResult(winningLotto);
    }

    public int count(Rank rank) {
        return winningResult.getOrDefault(rank, 0);
    }

    public int calculateProfit() {
        int profit = 0;
        for (Rank rank : winningResult.keySet()) {
            profit += rank.getReward() * winningResult.get(rank);
        }

        return profit;
    }

    public double calculateProfitLate(int money) {
        int profit = calculateProfit();
        return (double)profit / money * 100;
    }
}
