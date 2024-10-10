package tests.conditions;

import com.codeborne.selenide.WebElementsCondition;

public class CustomElementsCollectionConditions {
    public static WebElementsCondition allElementsIsVisible() {
        return new AllElementsInCollectionIsVisible();
    }

    public static WebElementsCondition elementsIsSortedAsc() {
        return new CollectionIsSortedAsc();
    }
}
