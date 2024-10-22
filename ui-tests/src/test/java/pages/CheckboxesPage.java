package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static utils.LocatorBuilder.getBuilder;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.util.Objects;

public class CheckboxesPage {

    SelenideElement checkboxLocator = $x("//form[@id='checkboxes']/input");
    SelenideElement checkbox1 = $(getBuilder().formCheckboxes().withIndex(1).build());
    SelenideElement checkbox2 = $(getBuilder().formCheckboxes().withIndex(2).build());
    SelenideElement pageTitle = $x("//h3");

    public CheckboxesPageAssert check() {
        return CheckboxesPageAssert.assertThat(this);
    }

    @Step("Нажать на checkbox {id}")
    public CheckboxesPage clickCheckbox(int id) {
        SelenideElement checkbox = getCheckboxByIndex(id);
        checkbox.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Кликнуть на чекбокс {id} и проверить его состояние.")
    public void setCheckboxWithVerification(int id) {
        SelenideElement checkbox = getCheckboxByIndex(id);
        boolean isCheckedBeforeClick = checkbox.isSelected();

        clickCheckbox(id);

        check().verifyCheckboxState(id, isCheckedBeforeClick);
    }

    @Step("Вывести в консоль значение атрибута checked для чекбокса {checkboxIndex}.")
    public CheckboxesPage printCheckedAttribute(int checkboxIndex) {
        SelenideElement checkbox = getCheckboxByIndex(checkboxIndex);
        boolean isChecked = checkbox.isSelected();
        System.out.println(
            "Чекбокс " + checkboxIndex + " " + (isChecked ? "отмечен" : "не отмечен"));
        return this;
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

    @Step("Кликнуть на чекбоксы в порядке {order}")
    public CheckboxesPage verifyCheckboxesByOrder(String order) {
        if (Objects.equals(order, "ascOrder")) {
            setCheckboxWithVerification(1);
            setCheckboxWithVerification(2);
        } else {
            setCheckboxWithVerification(2);
            setCheckboxWithVerification(1);
        }
        return this;
    }

}
