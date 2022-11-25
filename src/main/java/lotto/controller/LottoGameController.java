package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;
import java.util.List;
import java.util.Map;

public class LottoGameController {

    public void playGame() {
        try {
            LottoPurchaseMoney lottoPurchaseMoney = createLottoPurchaseMoney();

            PlayerLotto playerLotto = createPlayerLotto(lottoPurchaseMoney);
            printPlayerLotto(playerLotto);

            WinningLotto winningLotto = createWinningLotto();

            LottoGameResult lottoGameResult = createLottoGameResult(playerLotto, winningLotto);
            printLottoGameResult(lottoGameResult, lottoPurchaseMoney);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private LottoPurchaseMoney createLottoPurchaseMoney() {
        int money = InputView.lottoPurchaseMoney();
        return new LottoPurchaseMoney(money);
    }

    private PlayerLotto createPlayerLotto(LottoPurchaseMoney lottoPurchaseMoney) {
        return new PlayerLotto(lottoPurchaseMoney);
    }

    private void printPlayerLotto(PlayerLotto playerLotto) {
        OutputView.printLottos(playerLotto);
    }

    private WinningLotto createWinningLotto() {
        List<Integer> winningNumbers = InputView.winningNumbers();
        int bonusNumber = InputView.bonusNumber();

        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private LottoGameResult createLottoGameResult(PlayerLotto playerLotto, WinningLotto winningLotto) {
        Map<Rank, Integer> winningResult = playerLotto.createWinningResult(winningLotto);
        return new LottoGameResult(winningResult);
    }

    private void printLottoGameResult(LottoGameResult lottoGameResult, LottoPurchaseMoney lottoPurchaseMoney) {
        OutputView.printTotalResult(lottoGameResult, lottoPurchaseMoney);
    }
}
