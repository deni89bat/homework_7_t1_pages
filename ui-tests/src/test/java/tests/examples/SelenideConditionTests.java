package tests.examples;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class SelenideConditionTests {

    @BeforeEach
    void setup() {
        Configuration.browser = "chrome";
        Configuration.timeout = 2000;
        open("https://the-internet.herokuapp.com/");

    }

    @Test
    void selenideConditionsTest() {
        SelenideElement title = $x("//h1");
        //title.should(Condition.text("Интернет"),Duration.ofSeconds(5));
        //title.shouldNot(text("Welcome to the-internet"));
        //title.should(Condition.not(text("Welcome to the-internet")));
        title.should(text("Welcome to the-internet"));
        title.should(Condition.visible)
            .should(Condition.enabled)
            .should(Condition.text("baba"));
    }

    @AfterEach
    void teardown() {
        closeWebDriver();
    }
}
