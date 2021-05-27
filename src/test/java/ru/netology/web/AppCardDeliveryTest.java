package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.format.DateTimeFormatter.ofPattern;

public class AppCardDeliveryTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldValidData() {
        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue("Москва");
        for (int i = 0; i < 9; i++){
            $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        }
        String date = LocalDate.now().plusDays(4).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue("Иван");
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue("+71112223344");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldWarningIfInvalidCity() {
        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue("Подмосковье");
        for (int i = 0; i < 9; i++){
            $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        }
        String date = LocalDate.now().plusDays(4).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue("Иван");
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue("+71112223344");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Забронировать")).click();
        $((".input_invalid")).shouldBe(exist);
    }

    @Test
    void shouldWarningIfInvalidName() {
        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue("Москва");
        for (int i = 0; i < 9; i++){
            $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        }
        String date = LocalDate.now().plusDays(4).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue("Ivan");
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue("+71112223344");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Забронировать")).click();
        $((".input_invalid")).shouldBe(exist);
    }

    @Test
    void shouldWarningIfInvalidPhone() {
        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue("Москва");
        for (int i = 0; i < 9; i++){
            $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        }
        String date = LocalDate.now().plusDays(4).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue("Иван");
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue("81112223344");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Забронировать")).click();
        $((".input_invalid")).shouldBe(exist);
    }

    @Test
    void shouldWarningIfCheckboxOff() {
        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue("Москва");
        for (int i = 0; i < 9; i++){
            $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        }
        String date = LocalDate.now().plusDays(4).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue("Иван");
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue("+71112223344");
        $$("button").find(exactText("Забронировать")).click();
        $((".input_invalid")).shouldBe(exist);
    }

    @Test
    void shouldWarningIfPastDate() {
        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue("Москва");
        for (int i = 0; i < 9; i++){
            $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        }
        String date = LocalDate.now().minusDays(1).format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue("Иван");
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue("+71112223344");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Забронировать")).click();
        $((".input_invalid")).shouldBe(exist);
    }

    @Test
    void shouldWarningIfDayToDay() {
        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue("Москва");
        for (int i = 0; i < 9; i++){
            $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        }
        String date = LocalDate.now().format(ofPattern("dd.MM.yyyy"));
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue("Иван");
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue("+71112223344");
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Забронировать")).click();
        $((".input_invalid")).shouldBe(exist);
    }
}
