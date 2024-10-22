package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DoNotOverrideStartMethod extends BasicTest {
    @Test
    @DisplayName("ТК который не перезаписывает")
    void test() {
        internetMainPage.clickCheckboxesButton();
        System.out.println("DoNotOverride");
    }
}
