package tests;

import dto.request.DTOUserRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Random;
import utils.RestApiBuilder;

public class AuthApi extends BasicApi{
Random random = new Random();

    @Step("Отправить POST запрос на создание нового пользователя")
    public static Response registerNewUser(String login, String password) {
        return new RestApiBuilder(config.baseURI()).build()
            .body(toJSON(new DTOUserRequest(login, password)))
            .post(config.registerEndpoint());
    }
    /*    public static Response registerNewUser(String login, String password) {
        return new RestApiBuilder(config.baseURI()).build()
            .body("""
                {
                  "username": "%s",
                  "password": "%s"
                }
                """.formatted(login, password))
            .post(config.registerEndpoint());
    }*/

    @Step("Отправить POST запрос на авторизацию пользователя")
    public static Response loginUser(String login, String password) {
        return new RestApiBuilder(config.baseURI()).build()
            .body(toJSON(new DTOUserRequest(login, password)))
            .post(config.loginEndpoint());
    }

}
