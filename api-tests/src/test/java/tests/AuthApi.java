package tests;

import endpoints.Urls;
import io.restassured.response.Response;
import utils.RestApiBuilder;

public class AuthApi {

    public static Response registerNewUser(String login, String password) {
        return new RestApiBuilder(BasicApi.config.baseURI()).build()
            .body("""
                {
                  "username": "%s",
                  "password": "%s"
                }
                """.formatted(login, password))
            .post(Urls.REGISTER);
    }

    public static Response loginUser(String login, String password) {
        return new RestApiBuilder(Urls.BASE).build()
            .body("""
                {
                  "username": "%s",
                  "password": "%s"
                }
                """.formatted(login, password))
            .post(Urls.LOGIN).then().log().all().extract().response();
    }

}
