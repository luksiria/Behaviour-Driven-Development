package delivery.page;

import com.codeborne.selenide.Selenide;
import delivery.data.DataGenerator;

public class LoginPage {
    public void tryLogin(DataGenerator.LogInfo info){
        Selenide.$("[data-test-id=login] input").setValue(info.getLogin());
        Selenide.$("[data-test-id=password] input").setValue(info.getPassword());
        Selenide.$("[data-test-id=action-login]").click();
    }
}
