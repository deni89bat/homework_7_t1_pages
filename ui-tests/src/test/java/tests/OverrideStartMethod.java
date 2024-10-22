package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OverrideStartMethod extends BasicTest {
    @Test
    @DisplayName("ТК который перезаписывает")
    void test() {
        internetMainPage.clickCheckboxesButton();
        System.out.println("DoNotOverride");
    }

    @Override
    void startMethod() {
        System.out.println("Не входить в систему");
    }
}
