package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataCard;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private SelenideElement paymentToDataCard = $(byText("Оплата по карте"));
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement ownerField = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvcField = $("[placeholder='999']");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement messageSuccess = $(".notification_status_ok");
    private SelenideElement messageError = $(".notification_status_error");
    private SelenideElement errorInput = $(".input__sub");

    public PaymentPage() {
        paymentToDataCard.shouldBe(visible);
    }

    public void paymentData(DataCard card) {
        cardNumberField.setValue(card.getNumberCard());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        ownerField.setValue(card.getOwner());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }

    public boolean messageSuccessIsVisible() {
        messageSuccess.waitUntil(visible, 12000);
        return true;
    }

    public boolean messageErrorIsVisible() {
        messageError.waitUntil(visible, 12000);
        return true;
    }

    public boolean errorInputIsVisible() {
        return errorInput.isDisplayed();
    }
}