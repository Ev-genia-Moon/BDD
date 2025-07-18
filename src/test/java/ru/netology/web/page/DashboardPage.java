package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.impl.Html.text;

public class DashboardPage {
    private final SelenideElement header = $("[data-test-id='dashboard']");
    private final ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    //private final SelenideElement reloadButton = $("[data-test-id='action-reload']");

    public DashboardPage() { header.shouldBe(visible); }

    private SelenideElement findCard(DataHelper.CardInfo cardInfo) {
        return cards.findBy(Condition.attribute("data-test-id", cardInfo.getTestId()));
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
       return extractBalance(findCard(cardInfo).getText());
    }

    //public void reloadDashboardPage() {
    //    reloadButton.click();
    //    header.shouldBe(visible);
    //}

    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        findCard(cardInfo).$("button").click();
        return new TransferPage();
    }

}
