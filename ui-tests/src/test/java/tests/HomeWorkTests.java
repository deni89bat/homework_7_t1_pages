package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.addAttachment;

public class HomeWorkTests {
    // Регистрация расширения для создания скриншотов
    @RegisterExtension
    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true).to("target/screenshots");

    @BeforeAll
    public static void setup() {
        // Настройка конфигурации Selenide
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = "eager";
        open("https://the-internet.herokuapp.com/");
    }

    @Test
    public void checkboxesTest() {
        SelenideElement checkboxesButton = $x("//a[@href='/checkboxes']");
        clickLink(checkboxesButton);
        setCheckbox();
    }

    @Step("1. Перейти на страницу {buttonName}")
    public void clickLink(SelenideElement buttonName) {
        buttonName.click();
    }

    @Step("2. Выделить первый чекбокс, снять выделение со второго чекбокса.")
    private void setCheckbox() {
        SelenideElement checkbox1 = $x("//form[@id='checkboxes']/input[1]");
        SelenideElement checkbox2 = $x("//form[@id='checkboxes']/input[2]");

        checkbox1.click();
        checkbox2.click();
    }

    //  скриншот к отчету Allure
    @AfterEach
    public void attachScreenshot() {
        File screenshotFile = Screenshots.takeScreenShotAsFile();
        try {
            byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath());
            addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshotBytes), "png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
