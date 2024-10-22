package tests.examples;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@Disabled
public class SeleniumTests {
    WebDriver wd;

    @BeforeEach
    void setup() {
        wd = new ChromeDriver();
        wd.get("https://the-internet.herokuapp.com/");
    }

    @Test
    public void test() {
        WebElement infiniteScrollBtn = wd.findElement(By.xpath("//a[@href='/infinite_scroll']"));
        infiniteScrollBtn.click();

        WebElement pageTitle = wd.findElement(By.xpath("//h3"));
        assert pageTitle.getText().equals("Infinite Scroll");
    }

    @AfterEach
    void tearDown() {
        wd.quit();
    }
}
