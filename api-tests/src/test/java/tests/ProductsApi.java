package tests;

import assertions.ProductsAssert;

import dto.request.DTOProductRequest;
import dto.response.DTOProductResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.List;

public class ProductsApi extends BasicApi {

    public ProductsApi(String token) {
        super();
    }

    @Step("Отправить запрос на получение списка товаров")
    public Response getProducts() {
        return getBuilder().get(config.productsEndpoint());
    }

    @Step("Отправить запрос на получение списка товаров")
    public Response getProductsWithoutAuth() {
        return getBuilderWithoutAuth().get(config.productsEndpoint());
    }

    @Step("Отправить POST запрос на создание нового товара")
    public Response addNewProduct(String name, String category, double price, double discount) {
        return getBuilder()
            .body(toJSON(new DTOProductRequest(name, category, price, discount)))
            .post(config.productsEndpoint());
    }

    @Step("Отправить запрос на получение информации о товаре по ID")
    public Response getProduct(int id) {
        return getBuilder().get(config.productsEndpoint() + "/%s".formatted(id));
    }

    @Step("Отправить запрос на обновление информации о товаре")
    public Response putProduct(int id, String name, String category, double price,
        double discount) {
        return getBuilder()
            .body(new DTOProductRequest(name, category, price, discount))
            .put(config.productsEndpoint() + "/%s".formatted(id));
    }

    @Step("Отправить запрос на удаление товара")
    public Response deleteProduct(int id) {
        return getBuilder()
            .delete(config.productsEndpoint() + "/%s".formatted(id));
    }

    @Step("Проверяем структуру ответа")
    public void verifyProductResponseStructureDTO(Response response, DTOProductResponse product) {
        ProductsAssert.assertThat(response).verifyProductResponseStructureDTO(product);
    }

    @Step("Проверяем структуру ответа, содержащего массив продуктов")
    public void verifyProductResponseStructureDTO(Response response,
        List<DTOProductResponse> products) {
        ProductsAssert.assertThat(response).verifyProductResponseStructureDTO(products);
    }

    @Step("Проверяем, что ID продукта в ответе совпадает с запрошенным")
    public void respIdEqualReqId(Response response, DTOProductResponse product, int id) {
        ProductsAssert.assertThat(response).verifyIdProduct(product, id);
    }

    @Step("Проверяем, что у каждого продукта уникальный ID")
    public void verifyUniqueId(Response response, List<DTOProductResponse> products) {
        ProductsAssert.assertThat(response).verifyUniqueId(products);
    }
}
