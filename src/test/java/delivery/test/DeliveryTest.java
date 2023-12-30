package delivery.test;

import com.codeborne.selenide.Selenide;
import delivery.page.DashboardPage;
import delivery.page.TransferPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyTest {

    private DashboardPage dashboardPage;
    private TransferPage transferPage;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        dashboardPage = new DashboardPage();
        transferPage = new TransferPage();
    }

    @Test
    @DisplayName("Should perform card transfer")
    void shouldPerformCardTransfer() {

        Selenide.$("[data-test-id=login] input").setValue("vasya");
        Selenide.$("[data-test-id=password] input").setValue("qwerty123");
        Selenide.$("[data-test-id=action-login]").click();
        Selenide.$("[data-test-id=code] input").setValue("12345");
        Selenide.$("[data-test-id=action-verify]").click();
        var beginBalance1 = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
        var beginBalance2 = dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        String fromCardNumber = "5559 0000 0000 0001";
        int transferAmount = ThreadLocalRandom.current().nextInt(0, beginBalance1);
        var expectedBalance1 = beginBalance1 - transferAmount;
        var expectedBalance2 = beginBalance2 + transferAmount;
        transferPage.transferMoney(fromCardNumber, transferAmount);
        var endBalance1 = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
        var endBalance2 = dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        assertEquals(expectedBalance1, endBalance1);
        assertEquals(expectedBalance2, endBalance2);
    }
}
