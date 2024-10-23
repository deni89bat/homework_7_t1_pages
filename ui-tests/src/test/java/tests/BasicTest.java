package tests;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.addAttachment;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.InternetMainPage;
import utils.UIProps;

public class BasicTest {

    protected InternetMainPage internetMainPage = new InternetMainPage();

    UIProps props = ConfigFactory.create(UIProps.class);

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = "eager";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
            .screenshots(true)
            .savePageSource(true));
    }

    @BeforeEach
    @Step("Открыть страницу 'https://the-internet.herokuapp.com/'")
    void openMainPage() {
        open(props.baseURL());
    }

    // Метод для добавления скриншота в отчет Allure
    public void attachScreenshot() {
        File screenshotFile = Screenshots.takeScreenShotAsFile();
        if (screenshotFile != null) {
            try {
                byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath());
                addAttachment("Финальный скриншот", "image/png",
                    new ByteArrayInputStream(screenshotBytes), "png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Скриншот не был создан, так как screenshotFile равен null.");
        }
    }

    @AfterEach
    @Step("Сделать скриншот и закрыть драйвер")
    void teardown() {
        attachScreenshot();
        closeWebDriver();
    }


}
