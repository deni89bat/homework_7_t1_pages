package tests;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.addAttachment;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.CheckboxesPage;
import pages.DropdownPage;

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
    @Description("""
        Перейти на страницу Dropdown. Выбрать первую опцию, вывести в консоль текущий текст элемента dropdown, выбрать вторую опцию, вывести в консоль текущий текст элемента dropdown.
        Проверять корректное состояние каждого dropDown после каждого нажатия на него.""")
    public void dropdownTest() {
        internetMainPage.clickDropdownButton();
        DropdownPage dropdownPage = new DropdownPage();

        dropdownPage.check()
            .pageTitleIsVisible()
            .pageTitleIsVisible("Dropdown List")
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
}
