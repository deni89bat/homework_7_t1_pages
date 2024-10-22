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

    public static void registerUserAndVerify(DTOUserRequest user, int expectedStatusCode,
        String expectedMessage) {
        Response response = registerNewUser(user);
        steps.verifyCodeAndMessage(response, expectedStatusCode, expectedMessage);
    }

    public static DTOUserRequest createAndRegisterNewUser() {
        DTOUserRequest user = DTOUserRequest.getRandomUser();
        registerUserAndVerify(user, 201, "User registered successfully");
        return user; // возвращаем созданного пользователя
    }

    public static void verifyLoginResponse(Response response) {
        token = getAuthToken(response);
        steps.verifyStatusCode(response, 200);
        steps.verifyToken(response, token);
    }

    public static String getAuthToken(Response response) {
        return token = response.then().extract().jsonPath().getString("access_token");
    }

    public static String authenticateUser(DTOUserRequest user) {
        Response loginResponse = AuthService.loginUser(user);
        steps.verifyStatusCode(loginResponse, 200);
        return getAuthToken(loginResponse);
    }

    @Step("Создать нового пользователя, зарегистрировать и авторизовать его")
    public static String createAndAuthenticateNewUser() {
        DTOUserRequest user = createAndRegisterNewUser();
        return authenticateUser(user);
    }
}
