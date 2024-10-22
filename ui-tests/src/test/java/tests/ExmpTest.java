package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExmpTest extends BasicTest {

    @Test
    @DisplayName("Пример")
    void test() {
        internetMainPage.clickCheckboxesButton()
            .check().checkboxesButtonIsVisible();
    }

}
