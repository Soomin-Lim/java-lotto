package lotto.domain;

import static lotto.domain.ErrorMessage.LOTTO_PURCHASE_MONEY_LESS_THAN_PRICE_ERROR_MESSAGE;
import static lotto.domain.ErrorMessage.LOTTO_PURCHASE_MONEY_UNIT_ERROR_MESSAGE;

public class LottoPurchaseMoney {
    static final int LOTTO_PRICE = 1000;

    private final int money;

    public LottoPurchaseMoney(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        validateLessThanLottoPrice(money);
        validateUnit(money);
    }

    private void validateLessThanLottoPrice(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(LOTTO_PURCHASE_MONEY_LESS_THAN_PRICE_ERROR_MESSAGE);
        }
    }

    private void validateUnit(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(LOTTO_PURCHASE_MONEY_UNIT_ERROR_MESSAGE);
        }
    }

    public int getLottoNumber() {
        return money / LOTTO_PRICE;
    }

    public int getMoney() {
        return money;
    }
}
