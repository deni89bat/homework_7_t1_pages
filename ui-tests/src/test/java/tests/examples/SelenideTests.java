package tests.examples;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

@Disabled
public class SelenideTests {
    SelenideElement infiniteScrollBtn = $x("//a[@href='/infinite_scroll']");
    SelenideElement pageTitle = $x("//h3");

    @BeforeEach
    void setup() {
        Configuration.browser = "chrome";
        open("https://the-internet.herokuapp.com/");
    }

    @Test
    void test() {

        infiniteScrollBtn.hover();
        infiniteScrollBtn.click();
        assert pageTitle.getText().equals("Infinite Scroll");
        //ElementsCollection linksList = $$x("//a");
        //linksList.first(3);
    }

    @AfterEach
    void teardown() {
        closeWebDriver();
    }
}
