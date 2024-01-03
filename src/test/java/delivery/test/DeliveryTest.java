package delivery.test;

import delivery.data.DataGenerator;
import delivery.page.DashboardPage;
import delivery.page.LoginPage;
import delivery.page.TransferPage;
import delivery.page.VerificationPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyTest {

    private DashboardPage dashboardPage;
    private TransferPage transferPage;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        DataGenerator.LogInfo user = DataGenerator.getLogInfo();
        LoginPage loginPage = new LoginPage();
        loginPage.tryLogin(user);
        DataGenerator.VerificationCode verCode = DataGenerator.getCode();
        VerificationPage verificationPage = new VerificationPage();
        verificationPage.tryVerification(verCode);
    }

    @Test
    @DisplayName("Should perform card transfer")
    void shouldPerformCardTransfer() {
        DataGenerator.CardInfo fc = DataGenerator.getFirstCardInfo();
        DataGenerator.CardInfo sc = DataGenerator.getSecondCardInfo();
        var dashboardPage = new DashboardPage();
        var beginBalance1 = dashboardPage.getCardBalance(fc);
        var beginBalance2 = dashboardPage.getCardBalance(sc);
        int transferAmount = DataGenerator.generateAmount(beginBalance1);
        var expectedBalance1 = beginBalance1 - transferAmount;
        var expectedBalance2 = beginBalance2 + transferAmount;
        dashboardPage.SelectCard(sc);
        var transferPage = new TransferPage();
        transferPage.transferMoney(fc.getCardNumber(), transferAmount);
        var endBalance1 = dashboardPage.getCardBalance(fc);
        var endBalance2 = dashboardPage.getCardBalance(sc);
        assertEquals(expectedBalance1, endBalance1);
        assertEquals(expectedBalance2, endBalance2);
    }
}
