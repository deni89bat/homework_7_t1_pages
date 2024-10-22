package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.APIConfig;
import dto.request.DTOUserRequest;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Random;
import listner.CustomTpl;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import services.AuthService;
import utils.RestApiBuilder;

public class BasicApi {

    public static APIConfig config;
    public static Random random;
    protected static String token;

    @BeforeAll
    static void setUp() {
        config = ConfigFactory.create(APIConfig.class, System.getProperties());
        RestAssured.baseURI = config.baseURI();

        if (config.loggingEnabled()) {
            RestAssured.filters(new RequestLoggingFilter(),
                new ResponseLoggingFilter(),
                CustomTpl.customLogFilter()
                    .withCustomTemplates());
        }

        token = AuthService.createAndAuthenticateNewUser();
        random = new Random();
    }

    static String getAuthToken() {
        String access_token = AuthService.loginUser(DTOUserRequest.getRandomUser()).jsonPath()
            .getString("access_token");
        System.out.println("access_token: " + access_token);
        return access_token;
    }

    protected static String toJSON(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected static <T> T toDTOObject(Response response, Class<T> t) {
        try {
            return new ObjectMapper().readValue(response.getBody().prettyPrint(), t);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static RequestSpecification getBuilder() {
        return new RestApiBuilder(config.baseURI())
            .addAuth(token)
            .build().accept("application/json");
    }

    public static RequestSpecification getBuilder(String access_token) {
        return new RestApiBuilder(config.baseURI())
            .addAuth(access_token)
            .build().accept("application/json");
    }

    public static RequestSpecification getBuilderWithoutAuth() {
        return new RestApiBuilder("http://9b142cdd34e.vps.myjino.ru:49268")
            .build();
    }
}
