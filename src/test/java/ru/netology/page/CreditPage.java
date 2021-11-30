package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataCard;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private SelenideElement creditToDataCard = $(byText("Кредит по данным карты"));
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement ownerField = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvcField = $("[placeholder='999']");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement messageSuccess = $(".notification_status_ok");
    private SelenideElement messageError = $(".notification_status_error");
    private SelenideElement errorInput = $(".input__sub");

    public CreditPage() {
        creditToDataCard.shouldBe(visible);
    }

    public void creditData(DataCard card) {
        cardNumberField.setValue(card.getNumberCard());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        ownerField.setValue(card.getOwner());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }

    public void messageSuccessIsVisible() {
        messageSuccess.should(disappear, Duration.ofSeconds(20));
    }

    public void messageErrorIsVisible() {
        messageError.should(disappear, Duration.ofSeconds(20));
    }

    public boolean errorInputIsVisible() {
        return errorInput.isDisplayed();
    }
}