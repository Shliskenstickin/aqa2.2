package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void test01() {
        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue("Москва");
//        String date = LocalDate.now().plusDays(4).toString();
//        $("[data-test-id=\"date\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue("Иван");
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue("+71112223344");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
