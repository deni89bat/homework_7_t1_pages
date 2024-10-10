package tests.examples;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.DragAndDropOptions;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

//@Disabled
public class ActionsTests {
    @Test
    void dragAndDrop() {
        Selenide.open("https://the-internet.herokuapp.com/");

        SelenideElement dragAndDropButton = $x("//a[@href='/drag_and_drop']");
        dragAndDropButton.should(visible).click();
        SelenideElement elementA = $x("//div[@id='column-a']");
        SelenideElement elementB = $x("//div[@id='column-b']");
        sleep(300);

        //Actions actions = new Actions(getWebDriver());
        Actions actions = actions();

        int xOfElement = elementA.getCoordinates().onPage().getX();
        int yOfElement = elementA.getCoordinates().onPage().getY();

        int xOfDestination = elementB.getCoordinates().onPage().getX();
        int yOfDestination = elementB.getCoordinates().onPage().getY();

        actions.clickAndHold(elementA).perform();
        for (int i = 0; i < 100; i++) {
            actions.moveToLocation((xOfDestination - xOfElement) / 100, (yOfDestination - yOfElement) / 100).perform();
        }
        actions.release().perform();

        //elementA.dragAndDrop(DragAndDropOptions.to(elementB));

        sleep(10000);
    }

    @Test
    void dragAndDropWithoutMethod() {
        // Открываем страницу
        Selenide.open("https://the-internet.herokuapp.com/");

        // Находим ссылку на страницу Drag and Drop и кликаем
        SelenideElement dragAndDropButton = $x("//a[@href='/drag_and_drop']");
        dragAndDropButton.shouldBe(visible).click();

        // Находим элементы A и B
        SelenideElement elementA = $x("//div[@id='column-a']");
        SelenideElement elementB = $x("//div[@id='column-b']");

        // Инициализируем Actions для выполнения последовательности действий
        Actions actions = new Actions(getWebDriver());

        // Выполняем перетаскивание элемента A на позицию B
        actions.clickAndHold(elementA)
                .moveToElement(elementB)
                .release()
                .build()
                .perform();

        // Проверяем, что элементы поменялись местами по тексту
        elementA.shouldHave(text("B"));
        elementB.shouldHave(text("A"));
    }



    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
