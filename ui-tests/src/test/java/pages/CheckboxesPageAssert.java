package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;

public class CheckboxesPageAssert extends AbstractAssert<CheckboxesPageAssert, CheckboxesPage> {

    public CheckboxesPageAssert(CheckboxesPage actual) {
        super(actual, CheckboxesPage.class);
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

    @Step("Проверить, что чекбокс {checkboxIndex} видим")
    public CheckboxesPageAssert checkboxIsVisible(int checkboxIndex) {
        SelenideElement checkbox = actual.getCheckboxByIndex(checkboxIndex);
        checkbox.shouldBe(Condition.visible);
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

}
