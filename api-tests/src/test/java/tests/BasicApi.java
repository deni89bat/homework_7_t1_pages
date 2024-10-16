package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tests.utils.RestApiBuilder;

public class BasicApi {

    protected String token;

    public BasicApi(String token) {
        this.token = token;
    }

    public RequestSpecification getBuilder() {
        return new RestApiBuilder("http://9b142cdd34e.vps.myjino.ru:49268")
            .addAuth(token)
            .build();
    }

    public RequestSpecification getBuilderWithoutAuth() {
        return new RestApiBuilder("http://9b142cdd34e.vps.myjino.ru:49268")
            .build();
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
            return new ObjectMapper().readValue(response.getBody().prettyPrint(),t);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
