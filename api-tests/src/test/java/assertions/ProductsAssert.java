package assertions;

import dto.response.DTOProductsItem;
import io.restassured.response.Response;
import java.util.List;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.junit.platform.commons.util.StringUtils;

public class ProductsAssert extends AbstractAssert<ProductsAssert, Response> {

    public ProductsAssert(Response actual) {
        super(actual, ProductsAssert.class);
    }

    public static ProductsAssert assertThat(Response actual) {
        return new ProductsAssert(actual);
    }

    public ProductsAssert verifyProductResponseStructureDTO(List<DTOProductsItem> products) {
        Assertions.assertThat(products)
            .as("Список продуктов не должен быть пустым")
            .isNotNull()
            .isNotEmpty()
            .allMatch(product -> product != null, "Все продукты не должны быть null")
            .allMatch(product -> StringUtils.isNotBlank(product.getName()),
                "Название продукта не должно быть пустым")
            .allMatch(product -> product.getPrice() > 0, "Цена продукта должна быть больше нуля");
        // Проверка наличия необходимых полей для каждого продукта, исключил проверки price и name так как они проверяются выше
        products.forEach(product -> {
            Assertions.assertThat(product)
                .as("Продукт должен иметь все необходимые поля")
                .hasFieldOrProperty("category")
                .hasFieldOrProperty("discount")
                .hasFieldOrProperty("id");
        });
        return this;
    }

    // метод для проверки одного продукта
    public ProductsAssert verifyProductResponseStructureDTO(DTOProductsItem product) {
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

        Assertions.assertThat(product.getName())
            .as("Название продукта не должно быть пустым")
            .isNotBlank();

        Assertions.assertThat(product.getPrice())
            .as("Цена продукта должна быть больше нуля")
            .isGreaterThan(0);

        return this;
    }

    public ProductsAssert verifyIdProduct(DTOProductsItem product, int expectedId) {
        Assertions.assertThat(product.getId())
            .as("ID продукта должен совпадать с указанным в запросе")
            .isEqualTo(expectedId);

        return this;
    }
}
