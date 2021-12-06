package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.HeadingPage;
import ru.netology.page.PaymentPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test // Операция одобрена банком при покупке по карте со статусом APPROVED
    void shouldApprovedCardPurchaseWithValidData() throws SQLException {
        HeadingPage headingPage = new HeadingPage();
        PaymentPage paymentPage = headingPage.transitionPaymentPage();
        paymentPage.paymentData(DataHelper.approvedCard());
        assertTrue(paymentPage.messageSuccessIsVisible(),"Успешно. Операция одобрена Банком.");
    }

    @Test // Операция отклонена банком при покупке по карте со статусом DECLINED
    void shouldDeclinedCardPurchaseWithValidData() throws SQLException {
        HeadingPage headingPage = new HeadingPage();
        PaymentPage paymentPage = headingPage.transitionPaymentPage();
        paymentPage.paymentData(DataHelper.declinedCard());
        assertTrue(paymentPage.messageErrorIsVisible(),"Ошибка! Банк отказал в проведении операции.");
    }

    @Test // Операция отклонена банком при покупке несуществующей картой
    void shouldInvalidCardPurchaseWithValidData() throws SQLException {
        HeadingPage headingPage = new HeadingPage();
        PaymentPage paymentPage = headingPage.transitionPaymentPage();
        paymentPage.paymentData(DataHelper.invalidCard());
        assertTrue(paymentPage.messageErrorIsVisible(),"Ошибка! Банк отказал в проведении операции.");
    }

    @Test // Сообщение об ошибке при указании прошедшего месяца 'Неверно указан срок действия карты'
    void shouldMessageInvalidPeriodOfCard() throws SQLException {
        HeadingPage headingPage = new HeadingPage();
        PaymentPage paymentPage = headingPage.transitionPaymentPage();
        paymentPage.paymentData(DataHelper.invalidMonthCard());
        assertTrue(paymentPage.errorInputIsVisible(),"Неверно указан срок действия карты");
    }

    @Test // Сообщение об ошибке при указании прошедшего года 'Истёк срок действия карты'
    void shouldMessageCardExpired() throws SQLException {
        HeadingPage headingPage = new HeadingPage();
        PaymentPage paymentPage = headingPage.transitionPaymentPage();
        paymentPage.paymentData(DataHelper.invalidYearCard());
        assertTrue(paymentPage.errorInputIsVisible(),"Истёк срок действия карты");
    }

    @Test // Сообщение об ошибке невалидных значениях в поле Владелец 'Неверный формат'
    void shouldMessageInvalidFormatOwner() throws SQLException {
        HeadingPage headingPage = new HeadingPage();
        PaymentPage paymentPage = headingPage.transitionPaymentPage();
        paymentPage.paymentData(DataHelper.invalidOwnerCard());
        assertTrue(paymentPage.errorInputIsVisible(),"Неверный формат");
    }

    @Test // Сообщение об ошибке невалидных значениях в поле CVC 'Неверный формат'
    void shouldMessageInvalidFormatCvc() throws SQLException {
        HeadingPage headingPage = new HeadingPage();
        PaymentPage paymentPage = headingPage.transitionPaymentPage();
        paymentPage.paymentData(DataHelper.invalidCvcCard());
        assertTrue(paymentPage.errorInputIsVisible(),"Неверный формат");
    }

    @Test // Сообщения 'Неверный формат' или 'Поле обязательно для заполнения' при незаполненных полях формы
    void shouldEmptyFormFields() throws SQLException {
        HeadingPage headingPage = new HeadingPage();
        PaymentPage paymentPage = headingPage.transitionPaymentPage();
        paymentPage.paymentData(DataHelper.emptyFieldsCard());
        assertTrue(paymentPage.errorInputIsVisible(), "messages");
    }
}