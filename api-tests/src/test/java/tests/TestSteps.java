package tests;

import assertions.BasicAssert;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class TestSteps {

    @Step("Проверка статус-кода")
    public void verifyStatusCode(Response response, int expectedStatusCode) {
        BasicAssert.assertThat(response)
            .statusCodeIsEqual(expectedStatusCode);
    }

    @Step("Проверка полученного сообщения")
    public void verifyMessage(Response response, String msg) {
        BasicAssert.assertThat(response).responseMessageContainText(msg);
    }

    public void verifyCodeAndMessage(Response response, int expectedStatusCode, String msg) {
        verifyStatusCode(response, expectedStatusCode);
        verifyMessage(response, msg);
    }

    @Step("Проверка получения токена")
    public void verifyToken(Response response, String token) {
        BasicAssert.assertThat(response).responseContainField("access_token");
        Assertions.assertThat(token).isNotNull();
    }

}
