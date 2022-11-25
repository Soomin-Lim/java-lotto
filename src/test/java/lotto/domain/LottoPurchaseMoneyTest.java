package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

class LottoPurchaseMoneyTest {

    @DisplayName("로또 구입 금액이 1,000 미만인 경우 예외가 발생한다.")
    @ValueSource(ints = {-123, 0, 999})
    @ParameterizedTest
    void createLottoPurchaseMoneyByNumberOfLessThanPrice(Integer input) {
        assertThatThrownBy(() -> new LottoPurchaseMoney(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 구입 금액이 1,000으로 나누어 떨어지지 않으면 예외가 발생한다.")
    @ValueSource(ints = {1001, 1500})
    @ParameterizedTest
    void createLottoPurchaseMoneyByInvalidUnitNumber(Integer input) {
        assertThatThrownBy(() -> new LottoPurchaseMoney(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 구입 금액만큼 로또를 발행하는지 개수를 검증한다.")
    @Test
    void getLottoNumber() {
        LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(3000);

        int result = lottoPurchaseMoney.getLottoNumber();

        assertThat(result).isEqualTo(3);
    }
}