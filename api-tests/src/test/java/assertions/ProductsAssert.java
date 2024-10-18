package assertions;

import io.restassured.response.Response;
import java.util.List;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import schema.Product;

public class ProductsAssert extends AbstractAssert<ProductsAssert, Response> {

    public ProductsAssert(Response actual) {
        super(actual, ProductsAssert.class);
    }

    public static ProductsAssert assertThat(Response actual) {
        return new ProductsAssert(actual);
    }

    public ProductsAssert verifyProductResponseStructure() {
        BasicAssert.assertThat(actual).isNotNull()
            .responseContainField("[0]"); // Проверяем, что первый элемент существует

        // Получаем список продуктов из ответа
        List<Product> actualProducts = actual.jsonPath().getList(".", Product.class);

        // Проверяем, что каждый элемент имеет нужные поля
        Assertions.assertThat(actualProducts)
            .as("Список продуктов не должен быть пустым")
            .isNotEmpty()
            .allSatisfy(product -> Assertions.assertThat(product)
                .as("Продукт не соответствует структуре")
                .isNotNull()
                .hasFieldOrProperty("category")
                .hasFieldOrProperty("discount")
                .hasFieldOrProperty("id")
                .hasFieldOrProperty("name")
                .hasFieldOrProperty("price"));

        return this;
    }

    public ProductsAssert verifyIdProduct(int expectedId) {
        List<Product> actualId = actual.jsonPath().getList(".", Product.class);

        BasicAssert.assertThat(actual).responseFieldIsEqual("id", expectedId);
        return this;
    }
}
