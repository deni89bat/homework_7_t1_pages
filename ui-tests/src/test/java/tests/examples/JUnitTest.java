package tests.examples;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class JUnitTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll Выполняется перед всеми тестами");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll выполняется после каждого тестового метода");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach Выполняется перед каждым тестовым методом");
    }

    @Test
    public void test1() {
        System.out.println("Выполнился первый тест1");
    }

    @Test
    public void test2() {
        System.out.println("Выполнился второй тест2");
    }

    @Test
    public void test3() {
        System.out.println("Выполнился третий тест3");
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach Выполняется после каждого тестового метода");
    }

}
