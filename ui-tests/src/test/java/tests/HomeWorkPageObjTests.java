package tests;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.CheckboxesPage;
import pages.DisappearingElementsPage;
import pages.DropdownPage;
import pages.InputsPage;

public class HomeWorkPageObjTests extends BasicTest {

    // Регистрация расширения для создания скриншотов
    @RegisterExtension
    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true).to(
        "target/screenshots");
    TestSteps steps = new TestSteps();

    @ParameterizedTest
    @ValueSource(strings = {"ascOrder", "descOrder"})
    @DisplayName("Checkboxes")
    @Feature("Checkboxes")
    @Story("Управление состоянием чекбоксов")
    @Description("""
        Перейти на страницу Checkboxes. Выделить первый чекбокс, снять выделение со второго чекбокса. Вывести в консоль значение атрибута checked для каждого чекбокса.
        Проверять корректное состояние каждого чекбокса после каждого нажатия на него. Запустить тест с помощью @ParametrizedTest, изменяя порядок нажатия на чекбоксы с помощью одного параметра.""")
    public void checkboxesTest(String order) {
        internetMainPage.clickCheckboxesButton();
        CheckboxesPage checkboxesPage = new CheckboxesPage();

        checkboxesPage.check()
            .checkboxesTitleIsVisible()
            .validateCheckboxesTitleText()
            .checkboxIsVisible(1)
            .checkboxHasText(1, "checkbox 1")
            .checkboxIsVisible(2)
            .checkboxHasText(2, "checkbox 2")
            .page()

            .verifyCheckboxesByOrder(order)
            .printCheckedAttribute(1)
            .printCheckedAttribute(2);
    }

    @Test
    @DisplayName("Dropdown")
    @Feature("Dropdown")
    @Story("Выбор опций из выпадающего списка")
    @Description("""
        Перейти на страницу Dropdown. Выбрать первую опцию, вывести в консоль текущий текст элемента dropdown, выбрать вторую опцию, вывести в консоль текущий текст элемента dropdown.
        Проверять корректное состояние каждого dropDown после каждого нажатия на него.""")
    public void dropdownTest() {
        internetMainPage.clickDropdownButton();
        DropdownPage dropdownPage = new DropdownPage();

        dropdownPage.check()
            .pageTitleIsVisible()
            .validateTitleText("Dropdown List")
            .page()

            .selectOption(1)
            .logSelectedOption()
            .check()
            .optionIsSelected("Option 1")
            .page()

            .selectOption(2)
            .logSelectedOption()
            .check()
            .optionIsSelected("Option 2");

    }

    @RepeatedTest(value = 10, name = "Запуск {currentRepetition} из {totalRepetitions}")
    @DisplayName("Disappearing Elements")
    @Feature("Disappearing Elements")
    @Story("Проверка исчезающих элементов на странице")
    @Description("""
        Перейти на страницу Disappearing Elements. Добиться отображения 5 элементов, максимум за 10 попыток, если нет, провалить тест с ошибкой.
        Для каждого обновления страницы проверять наличие 5 элементов. Использовать @RepeatedTest""")
    public void disappearingElementsTest() {
        Configuration.timeout = 500;
        internetMainPage.clickDisappearingElementsButton();
        DisappearingElementsPage disappearingElementsPage = new DisappearingElementsPage();

        disappearingElementsPage.check()
            .pageTitleIsVisible()
            .validateTitleText("Disappearing Elements")
            .descriptionIsVisible()
            .validateDescriptionText(
                "This example demonstrates when elements on a page change by disappearing/reappearing on each page load.")
            .allElementsAreVisible();
    }

    @TestFactory
    @DisplayName("Inputs")
    @Feature("Inputs")
    @Story("Проверка различных значений для поля ввода на странице Inputs")
    @Description("""
        Перейти на страницу Inputs. Ввести любое случайное число от 1 до 10 000. Вывести в консоль значение элемента Input.
        Добавить проверки в задание Inputs из предыдущей лекции. Проверить, что в поле ввода отображается именно то число, которое было введено.
        Повторить тест 10 раз, используя @TestFactory, с разными значениями, вводимыми в поле ввода.
        Создать проверку негативных кейсов (попытка ввести в поле латинские буквы, спецсимволы, пробел до и после числа).""")
    List<DynamicTest> inputsTestFactoryTest() {
        List<DynamicTest> tests = new ArrayList<>();
        InputsPage inputsPage = new InputsPage();

        for (int i = 1; i <= 10; i++) {
            final int iteration = i;
            tests.add(DynamicTest.dynamicTest(
                "Тест №" + iteration + ": Ввести любое случайное число от 1 до 10 000.",
                () -> {
                    if (iteration == 1) {
                        internetMainPage.clickInputsButton(); // Кликаем на ссылку только в первом тесте
                        inputsPage.pageIsPresent(); // страница отобразилась со всеми элементами
                    }
                    inputsPage.clearInputField()
                        .enterRandomNumberInInput();
                }));
        }

        // Негативные кейсы: ввод букв, символов и пробелов
        List<String> negativeValues = List.of("abc",
            "#$%",
            " 123",
            "123 ",
            "123abc",
            "",
            "   ",
            "-123");

        for (String value : negativeValues) {
            tests.add(DynamicTest.dynamicTest("Негативный тест с вводом: '" + value + "'",
                () -> {
                    inputsPage.clearInputField()
                        .enterValue(value)
                        .checkInvalidInput(value);
                }));
        }

        return tests;
    }

}
