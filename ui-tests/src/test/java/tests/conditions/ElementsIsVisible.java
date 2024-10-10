package tests.conditions;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.WebElementCondition;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

public class ElementsIsVisible extends WebElementCondition {
    boolean expectedValue;
    protected ElementsIsVisible(String name) {
        super(name);
    }

    @NotNull
    @Override
    public CheckResult check(Driver driver, WebElement webElement) {
        Object actualValue = webElement.isDisplayed();
        expectedValue = true;
        boolean passed = webElement.isDisplayed();
        return new CheckResult(passed, actualValue);
    }

    @Override
    @NotNull
    public  String toString() {
        return String.valueOf(expectedValue);
    }
}
