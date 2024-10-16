package tests.conditions;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.WebElementsCondition;
import java.util.List;
import javax.annotation.CheckReturnValue;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

public class AllElementsInCollectionIsVisible extends WebElementsCondition {

    @NotNull
    @CheckReturnValue
    @Override
    public CheckResult check(Driver driver, List<WebElement> elements) {
        boolean result = isAllElementsVisible(elements);
        return new CheckResult(result, elements);
    }

    @Override
    public String toString() {
        return "All Elements in collection is visible.";
    }

    @Override
    public String errorMessage() {
        return "Elements list is all visible";
    }

    private boolean isAllElementsVisible(List<WebElement> elements) {
        for (WebElement element : elements) {
            if (!element.isDisplayed()) {
                return false;
            }
        }
        return true;
    }
}
