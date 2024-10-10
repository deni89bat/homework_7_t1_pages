package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.interactions.Actions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.addAttachment;
import static tests.conditions.CustomElementConditions.*;

public class HomeWorkActionsTests {
    // Регистрация расширения для создания скриншотов
    @RegisterExtension
    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true).to("target/screenshots");

    @BeforeAll
    public static void setup() {
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = "eager";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @BeforeEach
    void openBaseUrl() {
        open("https://the-internet.herokuapp.com/");
    }

    @Test
    @DisplayName("Drag and Drop")
    @Description("""
            Перейти на страницу Drag and Drop.
            Перетащить элемент A на элемент B.
            Задача на 10 баллов – сделать это, не прибегая к методу DragAndDrop();
            Проверить, что элементы поменялись местами""")
    public void dragAndDropTest() {
        SelenideElement dragAndDropButton = $x("//a[@href='/drag_and_drop']");
        SelenideElement elementA = $x("//div[@id='column-a']");
        SelenideElement elementB = $x("//div[@id='column-b']");

        clickLink(dragAndDropButton, dragAndDropButton.getText());
        moveElementAndCheck(elementA, elementB);

    }

    @Step("Перемещаем элемент A на элемент B. Проверяем, что элементы поменялись местами")
    private void moveElementAndCheck(SelenideElement holdElement, SelenideElement targetElement) {
        String holdElementText = holdElement.getText();
        String targetElementText = targetElement.getText();
        Actions actions = new Actions(getWebDriver());

        actions.clickAndHold(holdElement)
                .moveToElement(targetElement)
                .release()
                .perform();

        holdElement.shouldHave(text(targetElementText));
        targetElement.shouldHave(text(holdElementText));
    }

    @Step("Перейти на страницу {buttonName}")
    private void clickLink(SelenideElement buttonElement, String buttonName) {
        buttonElement.should(visible()).click();
    }

    // Метод для добавления скриншота в отчет Allure
    public void attachScreenshot() {
        File screenshotFile = Screenshots.takeScreenShotAsFile();
        if (screenshotFile != null) {
            try {
                byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath());
                addAttachment("Финальный скриншот", "image/png", new ByteArrayInputStream(screenshotBytes), "png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Скриншот не был создан, так как screenshotFile равен null.");
        }
    }

    @AfterEach
    void teardown() {
        attachScreenshot();
        closeWebDriver();
    }
}
