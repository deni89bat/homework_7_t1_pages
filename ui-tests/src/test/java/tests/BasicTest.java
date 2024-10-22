package tests;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.InternetMainPage;

public class BasicTest {

    protected InternetMainPage internetMainPage = new InternetMainPage();

    @BeforeEach
    @Step("Открыть страницу 'https://the-internet.herokuapp.com/'")
    void openMainPage() {
        open("https://the-internet.herokuapp.com/");
        Configuration.timeout = 10000;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
            .screenshots(true)
            .savePageSource(true));
    }

    @BeforeEach
    @Step("Войти в систему")
    void loginSystem() {
        startMethod();
    }

    @AfterEach
    @Step("Закрыть драйвер")
    void tearDown() {
        closeWebDriver();
    }

    void startMethod() {
        System.out.println("Вошли в систему");
    }
}
