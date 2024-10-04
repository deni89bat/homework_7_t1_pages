package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.codeborne.selenide.Selenide.*;
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
    }

    @BeforeEach
    void openBaseUrl() {
        open("https://the-internet.herokuapp.com/");
    }

    @Test
    @DisplayName("Checkboxes")
    @Description("Перейти на страницу Checkboxes. Выделить первый чекбокс, снять выделение со второго чекбокса. Вывести в консоль состояние атрибута checked для каждого чекбокса.")
    public void checkboxesTest() {
        SelenideElement checkboxesButton = $x("//a[@href='/checkboxes']");
        SelenideElement checkbox1 = $x("//form[@id='checkboxes']/input[1]");
        SelenideElement checkbox2 = $x("//form[@id='checkboxes']/input[2]");

        clickLink(checkboxesButton, checkboxesButton.getText());
        setCheckbox(checkbox1, checkbox2);
        printCheckedStatus(checkbox1, checkbox2);
    }

    @Test
    @DisplayName("Dropdown")
    @Description("Перейти на страницу Dropdown. Выбрать первую опцию, вывести в консоль текущий текст элемента dropdown, выбрать вторую опцию, вывести в консоль текущий текст элемента dropdown.")
    public void dropdownTest() {
        SelenideElement dropdownButton = $x("//a[@href='/dropdown']");
        SelenideElement dropdownElement = $x("//select[@id='dropdown']");

        clickLink(dropdownButton, dropdownButton.getText());
        selectOption(dropdownElement, 1);
        selectOption(dropdownElement, 2);
    }

    @Test
    @DisplayName("Disappearing Elements")
    @Description("Перейти на страницу Disappearing Elements. Добиться отображения 5 элементов, максимум за 10 попыток, если нет, провалить тест с ошибкой.")
    public void disappearingElementsTest() {
        SelenideElement disappearingElementsButton = $x("//a[@href='/disappearing_elements']");
        clickLink(disappearingElementsButton, disappearingElementsButton.getText());
        check5Elements();
    }

    @Step("1. Перейти на страницу {buttonName}")
    public void clickLink(SelenideElement buttonElement, String buttonName) {
        buttonElement.click();  // Кликаем по кнопке
    }

    @Step("2. Выделить первый чекбокс, снять выделение со второго чекбокса.")
    private void setCheckbox(SelenideElement checkbox1, SelenideElement checkbox2) {
        checkbox1.click();
        checkbox2.click();
    }

    @Step("3. Вывести в консоль состояние атрибута checked для каждого чекбокса.")
    private void printCheckedStatus(SelenideElement checkbox1, SelenideElement checkbox2) {
        // Проверка состояния чекбоксов и вывод в консоль
        boolean isChecked1 = checkbox1.isSelected();
        boolean isChecked2 = checkbox2.isSelected();

        System.out.println("Checkbox 1 выделен: " + isChecked1);
        System.out.println("Checkbox 2 выделен: " + isChecked2);
    }

    @Step("Выбрать опцию, вывести в консоль текущий текст элемента dropdown")
    private void selectOption(SelenideElement dropdownElement, int optionNumber) {
        dropdownElement.selectOption(optionNumber);
        System.out.println("В выпадающем списке выбрана опция: " + dropdownElement.getText());
    }

    @Step("Добиться отображения 5 элементов, максимум за 10 попыток, если нет, провалить тест с ошибкой.")
    private void check5Elements() {
        int numberOfAttempts = 10;
        boolean fiveElementsFound = false;

        for (int i = 1; i <= numberOfAttempts; i++) {
            int countOfElements = $$x("//li/a").size();

            if (countOfElements == 5) {
                fiveElementsFound = true;
                System.out.println("Найдено 5 элементов на попытке №" + (i));
                break;
            }
            refresh();
        }
        if (!fiveElementsFound) {
            throw new AssertionError("Не удалось найти 5 элементов за 10 попыток");
        }
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
