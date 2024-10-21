package tests;

import dto.request.DTOAddToCartRequest;
import dto.request.DTOProductRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class CartApi extends BasicApi {

    public CartApi(String token) {
        super();
    }

    @Step("Отправить запрос на получение корзины пользователя")
    public Response getUserCart() {
        return getBuilder().get(config.cartEndpoint());
    }

    @Step("Отправить запрос на добавления продукта в корзину пользователя")
    public Response addProductToCart(int productId, int quantity) {
        return getBuilder()
            .body(toJSON(new DTOAddToCartRequest(productId, quantity)))
            .post(config.cartEndpoint());
    }
}
