package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class SimpleUITest {

    @BeforeAll
    public static void setup() {
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    public void googleSearch() {
        openGoogleHomePage();
        searchFor("Т1");
        verifyFirstResult("Т1");
    }

    @Step("Открытие главной страницы Google")
    public void openGoogleHomePage() {
        open("https://www.google.com");
    }

    @Step("Поиск по ключевому слову: {searchQuery}")
    public void searchFor(String searchQuery) {
        $x("//textarea[@aria-label=\"Найти\"]").setValue(searchQuery).pressEnter();
    }

    @Step("Проверка, что 1ый результат содержит текст: {expectedText}")
    public void verifyFirstResult(String expectedText) {
        $x("(//div[@class='g']//h3)[1]").shouldHave(text(expectedText));
    }
}
