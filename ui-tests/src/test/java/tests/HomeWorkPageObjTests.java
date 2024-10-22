package tests;

import static io.qameta.allure.Allure.addAttachment;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.qameta.allure.Description;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.CheckboxesPage;

public class HomeWorkPageObjTests extends BasicTest {

    // Регистрация расширения для создания скриншотов
    @RegisterExtension
    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true).to(
        "target/screenshots");
    TestSteps steps = new TestSteps();

    @ParameterizedTest
    @ValueSource(strings = {"ascOrder", "descOrder"})
    @DisplayName("Checkboxes")
    @Description("""
        Перейти на страницу Checkboxes. Выделить первый чекбокс, снять выделение со второго чекбокса. Вывести в консоль значение атрибута checked для каждого чекбокса.
        Проверять корректное состояние каждого чекбокса после каждого нажатия на него. Запустить тест с помощью @ParametrizedTest, изменяя порядок нажатия на чекбоксы с помощью одного параметра.""")
    public void checkboxesTest(String order) {

        internetMainPage.clickCheckboxesButton();

        CheckboxesPage checkboxesPage = new CheckboxesPage();

        checkboxesPage.check()
            .checkboxesTitleIsVisible()
            .checkboxesTitleText()
            .checkboxIsVisible(1)
            .checkboxHasText(1, "Checkbox 1")
            .checkboxIsVisible(2)
            .checkboxHasText(2, "Checkbox 2")
            .page()
            .verifyCheckboxesByOrder(order)
            .printCheckedAttribute(1)
            .printCheckedAttribute(2);
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
}
