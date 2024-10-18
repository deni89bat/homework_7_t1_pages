package tests;

import assertions.BasicAssert;
import assertions.ProductsAssert;
import endpoints.Urls;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ProductsApi extends BasicApi {

    public ProductsApi(String token) {
        super();
    }

    public Response getProducts() {
        return getBuilder().get(Urls.PRODUCTS);
    }

    @Step("Отправить запрос на получение списка товаров")
    public Response getProductsWithoutAuth() {
        return getBuilderWithoutAuth().get(Urls.PRODUCTS)
            .then().log().all().extract().response();
    }

    @Step("Проверяем структуру ответа")
    public void verifyProductResponseStructure(Response response) {
        ProductsAssert.assertThat(response).verifyProductResponseStructure();
    }


    @Step("Отправить POST запрос на создание нового товара")
    public Response addNewProduct(String name, String category, double price, int discount) {
        return getBuilder().body("""
                {
                  "name": "%s",
                  "category": "%s",
                  "price": %s,
                  "discount": %s
                }
                """.formatted(name, category, price, discount)).post(Urls.PRODUCTS)
            .then().log().all().extract().response();
    }

    @Step("Отправить запрос на получение информации о товаре по ID")
    public Response getProduct(int id) {
        return getBuilder().get(Urls.PRODUCTS + "/%s".formatted(id))
            .then().log().all().extract().response();
    }

    @Step("Проверяем что ID продукта в ответе совпадает с запрошенным")
    public void respIdEqualReqId(Response response, int id) {
        BasicAssert.assertThat(response).responseFieldIsEqual("id", id);
    }


}
