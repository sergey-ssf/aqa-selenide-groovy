package ru.netolology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {

    String generateDate(int daysToAd, String pattern) {
        return LocalDate.now().plusDays(daysToAd).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test

    public void shouldRegisterTest() {

        int daysToAdd = 4;
        String pattern = "dd.MM.yyyy";
        String dateArranged = generateDate(daysToAdd, pattern);

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Самара");
        actions().sendKeys(Keys.DOWN, Keys.ENTER).perform();
        // actions().sendKeys(Keys.DOWN, Keys.ENTER).perform();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, Keys.SHIFT, Keys.LEFT), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dateArranged);
        $("[data-test-id='name'] input").setValue("Иванов Василий");
        $("[data-test-id='phone'] input").setValue("+79997654321");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).shouldBe(visible).click();
        // TODO:
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + dateArranged), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        //        $("[data-test-id='notification']").find(withText(dateEntered)).should(visible);

    }
}
