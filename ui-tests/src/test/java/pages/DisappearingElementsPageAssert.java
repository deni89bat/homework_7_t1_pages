package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;

public class DisappearingElementsPageAssert extends
    AbstractAssert<DisappearingElementsPageAssert, DisappearingElementsPage> {

    public DisappearingElementsPageAssert(DisappearingElementsPage actual) {
        super(actual, DisappearingElementsPageAssert.class);
    }

    public static DisappearingElementsPageAssert assertThat(DisappearingElementsPage actual) {
        return new DisappearingElementsPageAssert(actual);
    }

    @Step("Пользователь видит, что заголовок страницы 'Disappearing Elements' отображается ")
    public DisappearingElementsPageAssert pageTitleIsVisible() {
        actual.pageTitle.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить, что заголовок содержит текст '{expectedText}'")
    public DisappearingElementsPageAssert validateTitleText(String expectedText) {
        actual.pageTitle.shouldHave(Condition.text(expectedText));
        return this;
    }

    @Step("Пользователь видит, что описание страницы отображается")
    public DisappearingElementsPageAssert descriptionIsVisible() {
        actual.pageDescription.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить, что текст описания страницы соответствует '{expectedText}'")
    public DisappearingElementsPageAssert validateDescriptionText(String expectedText) {
        actual.pageDescription.shouldHave(Condition.text(expectedText));
        return this;
    }


    @Step("Проверить, что все элементы списка видимы")
    public DisappearingElementsPageAssert allElementsAreVisible() {
        actual.disappearingElementsList.shouldBe(CollectionCondition.size(5));
        actual.disappearingElementsList.forEach(element -> element.shouldBe(Condition.visible));
        return this;
    }
}


