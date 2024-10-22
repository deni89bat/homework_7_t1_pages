package services;

import dto.request.DTOUserRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import tests.BasicApi;
import tests.TestSteps;
import utils.RestApiBuilder;

public class AuthService extends BasicApi {
    static TestSteps steps = new TestSteps();

    @Step("Отправить POST запрос на создание нового пользователя")
    public static Response registerNewUser(DTOUserRequest user) {
        return new RestApiBuilder(config.baseURI()).build()
            .body(toJSON(user))
            .post(config.registerEndpoint());
    }

    @Step("Отправить POST запрос на авторизацию пользователя")
    public static Response loginUser(DTOUserRequest user) {
        return new RestApiBuilder(config.baseURI()).build()
            .body(toJSON(user))
            .post(config.loginEndpoint());
    }

    public static void registerUserAndVerify(DTOUserRequest user, int expectedStatusCode, String expectedMessage) {
        Response response = registerNewUser(user);
        steps.verifyCodeAndMessage(response,expectedStatusCode,expectedMessage);
    }

    public static DTOUserRequest createAndRegisterNewUser() {
        DTOUserRequest user = DTOUserRequest.getRandomUser();
        registerUserAndVerify(user, 201, "User registered successfully");
        return user; // возвращаем созданного пользователя
    }

    public static void verifyLoginResponse(Response response) {
        String token = response.then().extract().jsonPath().getString("access_token");
        steps.verifyStatusCode(response, 200);
        steps.verifyToken(response, token);
    }
}
