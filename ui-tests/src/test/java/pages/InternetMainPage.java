package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static utils.LocatorBuilder.getBuilder;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import io.qameta.allure.Step;

public class InternetMainPage {

    SelenideElement checkboxesButton = $x("//a[@href='/checkboxes']");
    //SelenideElement checkboxesNewButton = $(getBuilder().div().dataTest().equal("checkbox").build());
    SelenideElement dropdownButton = $x("//a[@href='/dropdown']");
    SelenideElement inputsButton = $x("//a[@href='/inputs']");

    public InternetMainPageAssert check() {
        return InternetMainPageAssert.assertThat(this);
    }

    @Step("Нажать на кнопку 'Checkboxes'")
    public InternetMainPage clickCheckboxesButton() {
        checkboxesButton.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Нажать на кнопку 'Dropdown'")
    public InternetMainPage clickDropdownButton() {
        dropdownButton.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Нажать на кнопку 'Inputs'")
    public InternetMainPage clickInputsButton() {
        inputsButton.shouldBe(Condition.visible).click();
        return this;
    }
}
