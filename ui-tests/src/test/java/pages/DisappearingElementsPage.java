package pages;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class DisappearingElementsPage {

    public ElementsCollection disappearingElementsList = $$x("//li/a");

    public SelenideElement pageTitle = $x("//h3");
    public SelenideElement pageDescription = $x(
        "//p[contains(text(), 'This example demonstrates when elements on a page change by disappearing/reappearing on each page load.')]");

    public DisappearingElementsPageAssert check() {
        return DisappearingElementsPageAssert.assertThat(this);
    }

}
