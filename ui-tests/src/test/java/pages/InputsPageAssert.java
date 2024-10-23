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

    @Step("Проверить, что заголовок страницы 'Inputs' отображается")
    public InputsPageAssert pageTitleIsVisible() {
        actual.pageTitle.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить, что поле ввода отображается")
    public InputsPageAssert inputFieldIsVisible() {
        actual.inputField.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить, что в поле ввода отображается значение '{expectedValue}'")
    public InputsPageAssert validateInputValue(String expectedValue) {
        actual.inputField.shouldHave(Condition.value(expectedValue));
        return this;
    }

    @Step("Проверить, что поле ввода очищено")
    public InputsPageAssert inputFieldIsEmpty() {
        actual.inputField.shouldHave(Condition.empty);
        return this;
    }
}
