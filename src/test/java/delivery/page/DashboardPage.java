package delivery.page;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public int getCardBalance(String id) {
        for (int i = 0; i < cards.size(); i++) {
            String dataTestId = cards.get(i).getAttribute("data-test-id");
            if (dataTestId.equals(id)) {
                String text = cards.get(i).getText();
                return extractBalance(text);
            }
        }
        return 1;
    }

    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value.trim());
    }
}
