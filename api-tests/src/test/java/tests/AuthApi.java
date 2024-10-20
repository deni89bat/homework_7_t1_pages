package tests;

import endpoints.Urls;
import io.restassured.response.Response;
import utils.RestApiBuilder;

public class AuthApi extends BasicApi{

    public static Response registerNewUser(String login, String password) {
        return new RestApiBuilder(config.baseURI()).build()
            .body("""
                {
                  "username": "%s",
                  "password": "%s"
                }
                """.formatted(login, password))
            .post(config.registerEndpoint());
    }

    public static Response loginUser(String login, String password) {
        return new RestApiBuilder(config.baseURI()).build()
            .body("""
                {
                  "username": "%s",
                  "password": "%s"
                }
                """.formatted(login, password))
            .post(config.loginEndpoint());
    }

}
