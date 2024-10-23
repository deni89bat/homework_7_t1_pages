package pages;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class InputsPage {

    public SelenideElement pageTitle = $x("//h3");
    public SelenideElement inputField = $x("//input[@type='number']");

    public InputsPageAssert check() {
        return InputsPageAssert.assertThat(this);
    }

    @Step("Очищаем поле ввода")
    public InputsPage clearInputField() {
        inputField.clear();
        return this;
    }

    @Step(
        "Ввести любое случайное число от 1 до 10 000. Вывести в консоль значение элемента Input." +
            "Проверить, что в поле ввода отображается именно то число, которое было введено.")
    public InputsPage enterRandomNumberInInput() {
        int randomNumber = (int) (Math.random() * 10000) + 1;
        inputField.sendKeys(String.valueOf(randomNumber));
        System.out.println("Значение элемента Input: " + inputField.getValue());
        inputField.shouldBe(Condition.value(String.valueOf(randomNumber)));
        return this;
    }

    @Step("Ввести значение {value}")
    public InputsPage enterValue(String value) {
        inputField.sendKeys(value);
        return this;
    }

    @Step("Проверка ввода недопустимого значения: '{value}'")
    public InputsPage checkInvalidInput(String value) {
        String expectedValue = value.replaceAll("[^0-9]", "");
        if (expectedValue.isEmpty()) {
            inputField.shouldBe(Condition.empty);
        } else {
            inputField.shouldBe(Condition.value(expectedValue));
        }
        return this;
    }


}
