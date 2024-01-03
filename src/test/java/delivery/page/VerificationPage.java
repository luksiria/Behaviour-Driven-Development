package delivery.page;

import com.codeborne.selenide.Selenide;
import delivery.data.DataGenerator;

public class VerificationPage {
    public void tryVerification(DataGenerator.VerificationCode code) {
        Selenide.$("[data-test-id=code] input").setValue(code.getCode());
        Selenide.$("[data-test-id=action-verify]").click();
    }
}
