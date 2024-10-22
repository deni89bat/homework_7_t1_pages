package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static utils.LocatorBuilder.getBuilder;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class CheckboxesPage {
    SelenideElement checkboxLocator =$x("//form[@id='checkboxes']/input") ;
    public SelenideElement checkbox1 =$(getBuilder().formCheckboxes().withIndex(1).build());
    public SelenideElement checkbox2 =$(getBuilder().formCheckboxes().withIndex(2).build());
    SelenideElement pageTitle = $x("//h3");

    public CheckboxesPageAssert check() {
            return CheckboxesPageAssert.assertThat(this);
        }

        @Step("Нажать на кнопку 'Checkboxes'")
        public pages.CheckboxesPage clickCheckboxesButton() {
            checkbox1.shouldBe(Condition.visible).click();
            return this;
        }

    @Step("Кликнуть на чекбокс и проверить его состояние.")
    public void setCheckboxWithVerification(SelenideElement checkbox) {
        boolean isCheckedBeforeClick = checkbox.isSelected();

        checkbox.click();
        if (isCheckedBeforeClick) {
            checkbox.shouldNotBe(Condition.checked);
        } else {
            checkbox.shouldBe(Condition.checked);
        }
    }

    @Step("Вывести в консоль значение атрибута checked для {checkboxName}.")
    public void printCheckedAttribute(SelenideElement checkbox, String checkboxName) {
        System.out.println("Значение атрибута checked для " + checkboxName + ": " + checkbox.getAttribute("checked"));
    }

    @Step("Проверить, что заголовок страницы отображается и содержит текст 'Checkboxes'")
    public CheckboxesPage verifyPageTitle() {
        pageTitle.shouldBe(Condition.visible)
            .shouldHave(Condition.text("Checkboxes"));
        return this;
    }
    public SelenideElement getCheckboxByIndex(int index) {
        if (index < 1 || index > 2) {
            throw new IllegalArgumentException("Индекс должен быть 1 или 2");
        }
        return index == 1 ? checkbox1 : checkbox2;
    }

}
