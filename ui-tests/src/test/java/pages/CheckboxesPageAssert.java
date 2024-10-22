package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;

public class CheckboxesPageAssert extends AbstractAssert<CheckboxesPageAssert, CheckboxesPage> {

    public CheckboxesPageAssert(CheckboxesPage actual) {
        super(actual, CheckboxesPageAssert.class);
    }

    public static CheckboxesPageAssert assertThat(CheckboxesPage actual) {
        return new CheckboxesPageAssert(actual);
    }

    public CheckboxesPage page() {
        return actual;
    }

    @Step("Пользователь видит заголовок 'Checkboxes'")
    public CheckboxesPageAssert checkboxesTitleIsVisible() {
        actual.pageTitle.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверяем, что заголовок страницы содержит текст 'Checkboxes'")
    public CheckboxesPageAssert validateCheckboxesTitleText() {
        actual.pageTitle.shouldHave(Condition.text("Checkboxes"));
        return this;
    }

    @Step("Пользователь видит checkbox {checkboxIndex}")
    public CheckboxesPageAssert checkboxIsVisible(int checkboxIndex) {
        SelenideElement checkbox = actual.getCheckboxByIndex(checkboxIndex);
        checkbox.shouldBe(Condition.visible);
        return this;
    }

    @Step("Пользователь видит, что текст чекбокса {checkboxIndex} равен '{expectedText}'")
    public CheckboxesPageAssert checkboxHasText(int checkboxIndex, String expectedText) {
        SelenideElement checkbox = actual.getCheckboxByIndex(checkboxIndex);
        checkbox.parent().shouldHave(Condition.text(expectedText));
        return this;
    }

    @Step("Проверить, что чекбокс {checkboxIndex} {isChecked}")
    public CheckboxesPageAssert isCheckboxChecked(int checkboxIndex, boolean isChecked) {
        SelenideElement checkbox = actual.getCheckboxByIndex(checkboxIndex);
        if (isChecked) {
            checkbox.shouldBe(Condition.checked);
        } else {
            checkbox.shouldNotBe(Condition.checked);
        }
        return this;
    }

    @Step("Проверить, что чекбокс {checkboxIndex} изменил свое состояние после клика")
    public CheckboxesPageAssert verifyCheckboxState(int checkboxIndex,
        boolean wasCheckedBeforeClick) {
        SelenideElement checkbox = actual.getCheckboxByIndex(checkboxIndex);

        if (wasCheckedBeforeClick) {
            checkbox.shouldNotBe(Condition.checked);
        } else {
            checkbox.shouldBe(Condition.checked);
        }

        return this;
    }

}
