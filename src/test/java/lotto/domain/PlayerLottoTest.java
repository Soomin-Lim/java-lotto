package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerLottoTest {

    @DisplayName("로또 구입 금액만큼 로또를 발행하는지 로또 발행 개수를 검증한다.")
    @Test
    void createPlayerLotto() {
        LottoPurchaseMoney money = new LottoPurchaseMoney(3000);
        PlayerLotto playerLotto = new PlayerLotto(money);

        int size = playerLotto.size();

        assertThat(size).isEqualTo(3);
    }
}