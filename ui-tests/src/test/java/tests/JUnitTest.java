package tests;

import org.junit.jupiter.api.*;

public class JUnitTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll Выполняется перед всеми тестами");
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

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll выполняется после каждого тестового метода");
    }

}
