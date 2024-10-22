package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;

public class DropdownPageAssert extends AbstractAssert<DropdownPageAssert, DropdownPage> {

    public DropdownPageAssert(DropdownPage actual) {
        super(actual, DropdownPageAssert.class);
    }

    public static DropdownPageAssert assertThat(DropdownPage actual) {
        return new DropdownPageAssert(actual);
    }

    public DropdownPage page() {
        return actual;
    }

    @Step("Проверить, что в выпадающем списке выбрана опция с текстом {expectedText}")
    public DropdownPageAssert optionIsSelected(String expectedText) {
        actual.dropdownElement.shouldHave(Condition.text(expectedText));
        return this;
    }
}
