package tests;

import assertions.BasicAssert;
import io.qameta.allure.Step;
import io.restassured.response.Response;

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


}
