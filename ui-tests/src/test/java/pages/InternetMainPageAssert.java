package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;

public class InternetMainPageAssert extends AbstractAssert<InternetMainPageAssert, InternetMainPage> {

    public InternetMainPageAssert(InternetMainPage actual) {
        super(actual, InternetMainPage.class);
    }

    public static InternetMainPageAssert assertThat(InternetMainPage actual) {
        return new InternetMainPageAssert(actual);
    }

    public InternetMainPage page() {
        return actual;
    }

    @Step("Пользователь видити кнопку 'Checkboxes'")
    public InternetMainPageAssert checkboxesButtonIsVisible() {
        actual.checkboxesButton.shouldBe(Condition.visible);
        return this;
    }

    @Step("Пользователь видит, что кнопка 'Checkboxes' выбрана")
    public InternetMainPageAssert checkboxesButtonIsSelected() {
        actual.checkboxesButton.should(Condition.attribute("checked","true"));
        return this;
    }

}
