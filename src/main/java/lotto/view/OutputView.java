package lotto.view;

import lotto.domain.*;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {

    private static final String ERROR_MESSAGE = "[ERROR] ";

    private static final String LOTTO_SIZE_OUTPUT_MESSAGE = "개를 구매했습니다.";
    private static final String LOTTO_PREFIX = "[";
    private static final String LOTTO_SUFFIX = "]";
    private static final String SEPARATOR = ", ";

    private static final String LOTTO_GAME_RESULT_START_MESSAGE = "당첨 통계\n---";

    public static void printErrorMessage(String errorMessage) {
        System.out.println(ERROR_MESSAGE + errorMessage);
    }

    public static void printLottos(PlayerLotto playerLotto) {
        System.out.println();

        printLottoSize(playerLotto.size());
        playerLotto.getLottos().forEach(OutputView::printLotto);
    }

    private static void printLottoSize(int size) {
        System.out.print(size);
        System.out.println(LOTTO_SIZE_OUTPUT_MESSAGE);
    }

    private static void printLotto(Lotto lotto) {
        StringBuilder lottoMessage = new StringBuilder(LOTTO_PREFIX);
        for (int number : lotto.getNumbers()) {
            lottoMessage.append(number).append(SEPARATOR);
        }
        lottoMessage = new StringBuilder(lottoMessage.substring(0, lottoMessage.length() - 2));
        lottoMessage.append(LOTTO_SUFFIX);
        System.out.println(lottoMessage);
    }

    public static void printTotalResult(LottoGameResult lottoGameResult, LottoPurchaseMoney lottoPurchaseMoney) {
        System.out.println();
        System.out.println(LOTTO_GAME_RESULT_START_MESSAGE);

        printWinningResult(lottoGameResult);
        printProfitLate(lottoGameResult, lottoPurchaseMoney);
    }

    private static void printWinningResult(LottoGameResult lottoGameResult) {
        DecimalFormat df = new DecimalFormat("###,###");
        
        System.out.println(Rank.FIFTH.getCount()+ "개 일치 (" + df.format(Rank.FIFTH.getReward()) + "원) - " + lottoGameResult.count(Rank.FIFTH) + "개");
        System.out.println(Rank.FOURTH.getCount()+ "개 일치 (" + df.format(Rank.FOURTH.getReward()) + "원) - " + lottoGameResult.count(Rank.FOURTH) + "개");
        System.out.println(Rank.THIRD.getCount()+ "개 일치 (" + df.format(Rank.THIRD.getReward()) + "원) - " + lottoGameResult.count(Rank.THIRD) + "개");
        System.out.println(Rank.SECOND.getCount()+ "개 일치, 보너스 볼 일치 (" + df.format(Rank.SECOND.getReward()) + "원) - " + lottoGameResult.count(Rank.SECOND) + "개");
        System.out.println(Rank.FIRST.getCount()+ "개 일치 (" + df.format(Rank.FIRST.getReward()) + "원) - " + lottoGameResult.count(Rank.FIRST) + "개");
    }

    private static void printProfitLate(LottoGameResult lottoGameResult, LottoPurchaseMoney lottoPurchaseMoney) {
        int money = lottoPurchaseMoney.getMoney();
        double profitRate = lottoGameResult.calculateProfitLate(money);

        System.out.print("총 수익률은 ");
        System.out.printf("%.1f", profitRate);
        System.out.print("%입니다.");
    }
}
