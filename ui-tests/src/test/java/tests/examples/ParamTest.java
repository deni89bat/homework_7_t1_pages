package tests.examples;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;

public class ParamTest {
    @ParameterizedTest
    @ValueSource(strings = {"String1", "String2"})
    void paramTest(String param) {
        System.out.println(param);
    }

    @ParameterizedTest
    @EnumSource(value = ENUM.class)
    public void paramEnumsTest(ENUM param) {
        System.out.println(param);
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void paramMethodSourceTest(String param) {
        System.out.println(param);
    }

    public static String[] getTestData() {
        return new String[]{"String1", "String2"};
    }
}
