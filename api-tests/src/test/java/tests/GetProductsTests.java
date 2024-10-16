package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.assertions.BasicAssert;
import tests.assertions.ProductsAssert;

public class GetProductsTests {

    ProductsApi productsApi;

    @BeforeEach
        //возможно лучше заюзать beforeAll
    void getAuthToken() {
        String token = AuthApi.loginUser("testUserName", "testPassword").jsonPath()
            .getString("access_token");
        productsApi = new ProductsApi(token);
    }

    @Test
    void test() {
        Response response = productsApi.getProducts();

        BasicAssert.assertThat(response)
            .statusCodeIsEqual(200);  // Проверка статус-кода

    }

    @Test
    void test2() {
        Response response = productsApi.getProduct(1).then()
            .log().all()  // Логирование запроса и ответа
            .extract().response();

//        BasicAssert.assertThat(response)
//            .statusCodeIsEqual(200)
//            .responseContainField("category")
//            .responseFieldIsEqual("[0].id", 1);

        ProductsAssert.assertThat(response).checkProductResponse("Electronics", "10.0", 1, "HP Pavilion Laptop");
    }

    //пример переопределения с невалидным токеном
    @Test
    void test3() {
        productsApi = new ProductsApi("апаааааа");

        Response response = productsApi.getProduct(1).then().log().all().extract().response();

        BasicAssert.assertThat(response).statusCodeIsEqual(401);
    }

}
