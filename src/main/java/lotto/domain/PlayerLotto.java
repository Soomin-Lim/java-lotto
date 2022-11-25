package lotto.domain;

import java.util.*;

public class PlayerLotto {

    private final List<Lotto> lottos;

    public PlayerLotto(LottoPurchaseMoney lottoPurchaseMoney) {
        this.lottos = issue(lottoPurchaseMoney);
    }

    public List<Lotto> issue(LottoPurchaseMoney lottoPurchaseMoney) {
        int number = lottoPurchaseMoney.getLottoNumber();

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            List<Integer> lottoNumbers = LottoNumberGenerator.generate();
            lottos.add(new Lotto(lottoNumbers));
        }

        return lottos;
    }

    public int size() {
        return lottos.size();
    }

    public Map<Rank, Integer> createWinningResult(WinningLotto winningLotto) {
        Map<Rank, Integer> result = new HashMap<>();

        lottos.forEach(lotto -> {
            Rank rank = winningLotto.compare(lotto);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        });

        return result;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

}
