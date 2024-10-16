package tests.utils;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestApiBuilder {

    private final RequestSpecification requestSpecification;

    public RestApiBuilder(String baseUrl) {
        requestSpecification = given().baseUri(baseUrl)
            .config(RestAssuredConfig
                .config()
                .httpClient(
                    HttpClientConfig
                        .httpClientConfig()
                        .setParam("http.connection.timeout", 5000)
                )
            )
            .contentType("application/json")
            .relaxedHTTPSValidation().log().all();
    }

    public RequestSpecification build() {
        return requestSpecification;
    }

    public RestApiBuilder addAuth(String token)  {
        requestSpecification.header("Authorization","Bearer " + token);

        return this;
    }

    public RestApiBuilder addHeader(String headerName, String headerValue) {
        requestSpecification.header(headerName, headerValue);

        return this;
    }

    public RestApiBuilder addCookies(String cookies) {
        requestSpecification.cookie(cookies);

        return this;
    }
}
