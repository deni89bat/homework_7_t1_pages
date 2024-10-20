package assertions;

import dto.response.DTOProductResponse;
import io.restassured.response.Response;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ProductsAssert extends AbstractAssert<ProductsAssert, Response> {

    public ProductsAssert(Response actual) {
        super(actual, ProductsAssert.class);
    }

    public static ProductsAssert assertThat(Response actual) {
        return new ProductsAssert(actual);
    }

    public ProductsAssert verifyProductResponseStructureDTO(DTOProductResponse product) {
        Assertions.assertThat(product)
            .as("Продукт не должен быть null")
            .isNotNull();

        Assertions.assertThat(product)
            .as("Продукт должен иметь все необходимые поля")
            .hasFieldOrProperty("category")
            .hasFieldOrProperty("discount")
            .hasFieldOrProperty("id")
            .hasFieldOrProperty("name")
            .hasFieldOrProperty("price");

        // Проверка, что название продукта не пустое
        Assertions.assertThat(product.getName())
            .as("Название продукта не должно быть пустым")
            .isNotBlank();

        // Проверка, что цена продукта больше нуля
        Assertions.assertThat(product.getPrice())
            .as("Цена продукта должна быть больше нуля")
            .isGreaterThan(0);

        return this;
    }

    // Проверка структуры массива продуктов
    public ProductsAssert verifyProductResponseStructureDTO(List<DTOProductResponse> products) {
        // Проверяем, что список продуктов не пустой
        Assertions.assertThat(products)
            .as("Список продуктов не должен быть пустым")
            .isNotEmpty();

        // Проверяем каждый продукт в списке
        for (DTOProductResponse product : products) {
            verifyProductResponseStructureDTO(product);
        }

        return this;
    }

    // Проверка уникальности ID
    public ProductsAssert verifyUniqueId(List<DTOProductResponse> products) {
        List<Integer> productIds = products.stream()
            .map(DTOProductResponse::getId)
            .collect(Collectors.toList());

        Assertions.assertThat(productIds)
            .as("ID продуктов должны быть уникальными")
            .doesNotHaveDuplicates();
        return this;
    }

    // Проверка ID продукта
    public ProductsAssert verifyIdProduct(DTOProductResponse product, int expectedId) {
        Assertions.assertThat(product.getId())
            .as("ID продукта должен совпадать с указанным в запросе")
            .isEqualTo(expectedId);

        return this;
    }
}
