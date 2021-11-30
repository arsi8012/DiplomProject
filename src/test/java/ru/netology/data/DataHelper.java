package ru.netology.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {
    private DataHelper() {
    }

    public static DataCard approvedCard() {
        return new DataCard("1111 2222 3333 4444", "12", "21", "Krylov", "123");
    }

    public static DataCard declinedCard() {
        return new DataCard("5555 6666 7777 8888", "12", "21", "Krylov", "123");
    }

    public static DataCard invalidCard() {
        return new DataCard("1111 5555 2222 6666", "12", "21", "Krylov", "123");
    }

    public static DataCard invalidOwnerCard() {
        return new DataCard("1111 2222 3333 4444", "12", "21", "Кры@ЛОВ!", "123");
    }

    public static DataCard invalidMonthCard() {
        LocalDate month = LocalDate.now().minusMonths(6);
        return new DataCard("1111 2222 3333 4444", month.format(DateTimeFormatter.ofPattern("MM")), "21", "Krylov", "123");
    }

    public static DataCard invalidYearCard() {
        LocalDate year = LocalDate.now().minusYears(3);
        return new DataCard("1111 2222 3333 4444", "12", year.format(DateTimeFormatter.ofPattern("yy")), "Krylov", "123");
    }

    public static DataCard invalidCvcCard() {
        return new DataCard("1111 2222 3333 4444", "12", "21", "Krylov", "12");
    }

    public static DataCard emptyFieldsCard() {
        return new DataCard("", "", "", "", "");
    }
}