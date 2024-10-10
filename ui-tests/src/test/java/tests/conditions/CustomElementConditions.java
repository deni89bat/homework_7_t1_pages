package tests.conditions;

import com.codeborne.selenide.WebElementCondition;

public class CustomElementConditions {

    public static WebElementCondition visible() {
        return new ElementsIsVisible("visible");
    }
}
