package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DbUtilsData;
import ru.netology.page.CreditPage;
import ru.netology.page.HeadingPage;
import ru.netology.page.PaymentPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class BuyTravelTest {

        @BeforeEach
        public void setOpen() throws SQLException {
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

        @Test // Покупка по карте со статусом APPROVED
        void shouldConfirmPaymentWithValidCard() throws SQLException {
            HeadingPage headingPage = new HeadingPage();
            PaymentPage paymentPage = headingPage.transitionPaymentPage();
            paymentPage.paymentData(DataHelper.approvedCard());
            val status = DbUtilsData.getStatus("1111 2222 3333 4444");
            assertEquals("APPROVED", status);
        }

        @Test // Покупка в кредит по карте со статусом DECLINED
        void shouldNotConfirmPaymentWithDeclinedCard() throws SQLException {
            HeadingPage headingPage = new HeadingPage();
            CreditPage creditPage = headingPage.transitionCreditPage();
            creditPage.creditData(DataHelper.declinedCard());
            val status = DbUtilsData.getStatusDb("5555 6666 7777 8888");
            assertEquals("DECLINED", status);
        }

        @Test // Покупка по несуществующей карте
        void shouldNotConfirmPaymentWithFakeCard() throws SQLException {
            DbUtilsData.cleanDatabase();
            HeadingPage headingPage = new HeadingPage();
            PaymentPage paymentPage = headingPage.transitionPaymentPage();
            paymentPage.paymentData(DataHelper.invalidCard());
            paymentPage.messageErrorIsVisible();
            assertEquals("0", DbUtilsData.countTotal());
        }
}
