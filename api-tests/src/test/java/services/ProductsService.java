package services;

import assertions.ProductsAssert;
import dto.response.DTOProductsItem;
import dto.response.DTOProductsList;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import tests.BasicApi;
import tests.TestSteps;

public class ProductsService extends BasicApi {

    static TestSteps steps = new TestSteps();

    @Step("Отправить запрос на создание нового продукта")
    public static Response addNewProduct() {
        dto.request.DTOProductsItem newProduct = new dto.request.DTOProductsItem("New Product",
            "Electronics", 12.99, 5);
        return getBuilder()
            .body(toJSON(newProduct))
            .post(config.productsEndpoint());
    }


    @Step("Отправить запрос на получение информации о товаре по ID")
    public static DTOProductsList getProductByRandomId() {
        int randomId = getRandomProductId();
        Response response = getBuilder().get(config.productsEndpoint() + "/%s".formatted(randomId));
        steps.verifyStatusCode(response, 200);
        List<DTOProductsItem> product = Arrays.asList(response.as(DTOProductsItem[].class));
        verifyProductResponseStructureDTO(response, product);
        respIdEqualReqId(response, product.get(0), randomId);
        return DTOProductsList.builder()
            .dtoProductsItemsList(product)
            .build();
    }

    @Step("Отправить запрос на получение списка продуктов")
    public static DTOProductsList getProductsWithoutAuth() {
        Response response = getBuilderWithoutAuth().get(config.productsEndpoint());
        steps.verifyStatusCode(response, 200);

        List<DTOProductsItem> products = Arrays.asList(response.as(DTOProductsItem[].class));
        verifyProductResponseStructureDTO(response, products);

        return DTOProductsList.builder()
            .dtoProductsItemsList(products)
            .build();
    }

    @Step("Отправить запрос на обновление информации о товаре")
    public static Response putProduct() {
        int randomId = getRandomProductId();
        return getBuilder()
            .body(toJSON(
                new dto.request.DTOProductsItem("Updated Product Name", "Electronics", 15.99, 8)))
            .put(config.productsEndpoint() + "/%s".formatted(randomId));
    }

    @Step("Отправить запрос на удаление товара")
    public static Response deleteProduct(int id) {
        return getBuilder()
            .delete(config.productsEndpoint() + "/%s".formatted(id));
    }

    @Step("Проверяем структуру ответа, содержащего массив продуктов")
    public static void verifyProductResponseStructureDTO(Response response,
        List<DTOProductsItem> products) {
        ProductsAssert.assertThat(response)
            .verifyProductResponseStructureDTO(products);
    }

    @Step("Проверяем, что ID продукта в ответе совпадает с запрошенным")
    public static void respIdEqualReqId(Response response, DTOProductsItem product, int id) {
        ProductsAssert.assertThat(response).verifyIdProduct(product, id);
    }

    private static int getRandomProductId() {
        DTOProductsList products = getProductsWithoutAuth();
        Random random = new Random();
        return random.nextInt(1, products.getDtoProductsItemsList().size() + 1);
    }

}
