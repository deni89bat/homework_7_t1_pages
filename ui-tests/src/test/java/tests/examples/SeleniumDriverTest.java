package tests.examples;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@Disabled
public class SeleniumDriverTest {

    WebDriver webDriver;

    @BeforeEach
    void driverInit() {
        webDriver = new ChromeDriver();
    }

    @Test
    public void testDriver() {
        webDriver.get("https://online.t1-academy.ru/");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        webDriver.navigate().refresh();

        WebElement element = webDriver.findElement(By.xpath("//input[@value='Войти в систему']"));
        element.click();

        WebElement loginInput = webDriver.findElement(
            By.xpath("//input[@id='user_login_form_login']"));
        WebElement labelOfLogin = webDriver.findElement(
            By.xpath("//label[@for='user_login_form_login']"));

        loginInput.sendKeys("sasasas");
        loginInput.sendKeys(Keys.BACK_SPACE);
        loginInput.clear();

        String textLabelOfLogin = labelOfLogin.getText();
        System.out.println(textLabelOfLogin);

        String attributeOfLoginInput = loginInput.getAttribute("name");
        System.out.println(attributeOfLoginInput);

        List<WebElement> inputsList = webDriver.findElements(By.xpath("//input"));
        System.out.println(inputsList.size());
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }
}
