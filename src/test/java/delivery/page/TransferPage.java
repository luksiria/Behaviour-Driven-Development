package delivery.page;

import com.codeborne.selenide.Selenide;

public class TransferPage {

    public void transferMoney(String fromCard, int amount) {
        Selenide.$("[data-test-id=amount] input").setValue(String.valueOf(amount));
        Selenide.$("[data-test-id=from] input").setValue(fromCard);
        Selenide.$("[data-test-id=action-transfer]").click();
    }
}
