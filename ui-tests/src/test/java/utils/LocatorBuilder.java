package utils;

import org.openqa.selenium.By;

public class LocatorBuilder {

    private final StringBuilder locator;

    public LocatorBuilder() {
        this.locator = new StringBuilder();
    }

    public static LocatorBuilder getBuilder() {
        return new LocatorBuilder();
    }

    // Базовый локатор для формы с чекбоксами
    public LocatorBuilder formCheckboxes() {
        locator.append("//form[@id='checkboxes']/input");
        return this;
    }

    // Метод для добавления индекса input
    public LocatorBuilder withIndex(int index) {
        locator.append("[").append(index).append("]");
        return this;
    }

    public By build() {
        return By.xpath(locator.toString());
    }
}
