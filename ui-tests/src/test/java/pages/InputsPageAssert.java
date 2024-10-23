package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;

public class InputsPageAssert extends AbstractAssert<InputsPageAssert, InputsPage> {

    public InputsPageAssert(InputsPage actual) {
        super(actual, InputsPageAssert.class);
    }

    public static InputsPageAssert assertThat(InputsPage actual) {
        return new InputsPageAssert(actual);
    }

    @Step("Пользователь видит, что заголовок страницы 'Inputs' отображается")
    public InputsPageAssert pageTitleIsVisible() {
        actual.pageTitle.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить, что заголовок содержит текст '{expectedText}'")
    public InputsPageAssert validateTitleText(String expectedText) {
        actual.pageTitle.shouldHave(Condition.text(expectedText));
        return this;
    }

    @Step("Пользователь видит, что поле ввода отображается")
    public InputsPageAssert inputFieldIsVisible() {
        actual.inputField.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить, что в поле ввода отображается значение '{expectedValue}'")
    public InputsPageAssert validateInputValue(String expectedValue) {
        actual.inputField.shouldHave(Condition.value(expectedValue));
        return this;
    }

    @Step("Пользователь видит, что название поля ввода отображается")
    public InputsPageAssert nameInputFieldIsVisible() {
        actual.nameInputField.shouldBe(Condition.visible);
        return this;
    }

    @Step("Пользователь видит, что название поля ввода {expectedText}")
    public InputsPageAssert validateNameInputFieldText(String expectedText) {
        actual.nameInputField.shouldHave(Condition.text(expectedText));
        return this;
    }


    @Step("Проверить, что поле ввода очищено")
    public InputsPageAssert inputFieldIsEmpty() {
        actual.inputField.shouldHave(Condition.empty);
        return this;
    }

    public InputsPage page() {
        return actual;
    }
}

