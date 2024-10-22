package services;

import dto.request.DTOAddToCartRequest;
import dto.response.DTOUserCartResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import tests.BasicApi;
import tests.TestSteps;

public class CartService extends BasicApi {

    static TestSteps steps = new TestSteps();

    @Step("Отправить запрос на получение корзины пользователя")
    public static Response getUserCart(String token) {
        return getBuilder(token).get(config.cartEndpoint());
    }

    @Step("Отправить запрос на получение пустой корзины пользователя")
    public static void getEmptyUserCart(String token) {
        Response getEmptyCarRresponse = getUserCart(token);
        steps.verifyCodeAndMessage(getEmptyCarRresponse, 404, "Cart not found");
    }

    @Step("Отправить запрос на получение не пустой корзины пользователя")
    public static DTOUserCartResponse getNotEmptyCart(String token) {
        Response response = getUserCart(token);
        steps.verifyStatusCode(response, 200);
        return response.as(DTOUserCartResponse.class);
    }


    @Step("Отправить запрос на добавления продукта в корзину пользователя")
    public static void addProductToCart(String token, int productId, int quantity) {
        Response response = getBuilder(token)
            .body(toJSON(new DTOAddToCartRequest(productId, quantity)))
            .post(config.cartEndpoint());

        steps.verifyCodeAndMessage(response, 201, "Product added to cart successfully");
    }

    @Step("Отправить запрос на удаление продукта по ID")
    public static void deleteProductFromCart(String token, int productId) {
        Response response = getBuilder(token)
            .delete(config.cartEndpoint() + "/%s".formatted(productId));

        steps.verifyCodeAndMessage(response, 200, "Product removed from cart");
    }


    @Step("Проверить что была получена валидная не пустая корзина")
    public static void verifyCart(DTOUserCartResponse cart) {
        Assertions.assertThat(cart)
            .isNotNull();

        Assertions.assertThat(cart.getCart())
            .isNotEmpty()  // Проверка, что список товаров в корзине не пуст
            .allSatisfy(item -> {  // Проверка каждого элемента корзины
                Assertions.assertThat(item.getId())
                    .isGreaterThan(0);  // Проверка, что у товара есть валидный id
                Assertions.assertThat(item.getName())
                    .isNotBlank();  // Проверка, что у товара есть имя
                Assertions.assertThat(item.getPrice())
                    .isGreaterThan(0);  // Проверка, что цена товара больше 0
            });

        Assertions.assertThat(cart.getTotal_price())
            .isGreaterThan(0);

        verifyQuantityInCart(cart);
    }

    @Step("Проверить, что ответ не содержит поле 'quantity'")
    public static void verifyQuantityInCart(DTOUserCartResponse cart) {
        Assertions.assertThat(toJSON(cart))
            .as("Поле 'quantity' присутствует в ответе")
            .doesNotContain("quantity");
    }

    @Step("Проверить, что в корзине есть добавленный продукт")
    public static void verifyAddedProductInCart(DTOUserCartResponse cart, int productId,
        int expectedQuantity) {
        Assertions.assertThat(cart.getCart()).as("В корзине нет добавленного продукта")
            .isNotEmpty()  // Проверка, что список товаров в корзине не пуст
            .anySatisfy(item -> {
                Assertions.assertThat(item.getId())
                    .isEqualTo(productId);  // Проверяем, что ID продукта соответствует ожидаемому
                Assertions.assertThat(item.getQuantity())
                    .isEqualTo(
                        expectedQuantity);  // Проверяем, что количество соответствует ожидаемому
            });
    }

}
