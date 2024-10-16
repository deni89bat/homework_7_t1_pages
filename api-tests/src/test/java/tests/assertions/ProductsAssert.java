package tests.assertions;

import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;

public class ProductsAssert extends AbstractAssert<ProductsAssert, Response> {

    public ProductsAssert(Response actual) {
        super(actual, ProductsAssert.class);
    }

    public static ProductsAssert assertThat(Response actual) {
        return new ProductsAssert(actual);
    }

    public ProductsAssert checkProductResponse(String category, String discount, int id, String name) {
        BasicAssert.assertThat(actual)
            .statusCodeIsEqual(200)
            .responseFieldIsEqual("[0].category", category)  // Проверка поля "category"
            .responseFieldIsEqual("[0].discount", discount)  // Проверка поля "discount"
            .responseFieldIsEqual("[0].id", id)  // Проверка поля "id"
            .responseFieldIsEqual("[0].name", name);  // Проверка поля "name"
            //.responseFieldIsEqual("[0].price", price);  // Проверка поля "price"
        return this;
    }
}
