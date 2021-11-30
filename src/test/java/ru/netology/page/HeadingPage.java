package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class HeadingPage {
    private SelenideElement heading = $$("h2").find(text("Путешествие дня"));
    private SelenideElement paymentButton = $$("button").find(exactText("Купить"));
    private SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

    public HeadingPage() {
        heading.shouldBe(visible);
    }

    public PaymentPage transitionPaymentPage() {
        paymentButton.click();
        return new PaymentPage();
    }

    public CreditPage transitionCreditPage() {
        creditButton.click();
        return new CreditPage();
    }
}