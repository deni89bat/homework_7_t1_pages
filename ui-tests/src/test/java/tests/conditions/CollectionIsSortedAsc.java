package tests.conditions;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.WebElementsCondition;
import java.util.Comparator;
import java.util.List;
import javax.annotation.CheckReturnValue;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

public class CollectionIsSortedAsc extends WebElementsCondition {

    List<WebElement> expectedValue;
    List<String> expectedValueStrings;

    @Override
    @CheckReturnValue
    @NotNull
    public CheckResult check(Driver driver, List<WebElement> elements) {
        expectedValue = getExpectedValue(elements);
        expectedValueStrings = expectedValue.stream().map(WebElement::getText).toList();
        boolean result = elements.equals(expectedValue);
        return new CheckResult(result, elements.stream().map(WebElement::getText).toList());
    }

    @Override
    public String toString() {
        return String.valueOf(expectedValueStrings);
    }

    private List<WebElement> getExpectedValue(List<WebElement> elements) {
        return elements.stream().sorted(Comparator.comparing(WebElement::getText)).toList();
    }

}
