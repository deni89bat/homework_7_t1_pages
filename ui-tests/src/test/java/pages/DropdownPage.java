package pages;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class DropdownPage {

    SelenideElement dropdownElement = $x("//select[@id='dropdown']");
    SelenideElement pageTitle = $x("//h3");

    public DropdownPageAssert check() {
        return DropdownPageAssert.assertThat(this);
    }

    @Step("Выбрать опцию {optionNumber} в выпадающем списке")
    public DropdownPage selectOption(int optionNumber) {
        dropdownElement.selectOption(optionNumber);
        return this;
    }

    @Step("Получить и вывести в консоль выбранный текст элемента dropdown")
    public DropdownPage logSelectedOption() {
        String selectedText = dropdownElement.getSelectedOptionText();
        System.out.println("В выпадающем списке выбрана опция: " + selectedText);
        return this;
    }
}
