package tests;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmokeApiTest {

    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    @Tag("smoke")
    public void getUserTest() {
        Response response = getUser(1);
        validateResponseStatus(response);
        String name = extractName(response);
        validateUserName(name);
    }

    @Step("Получение пользователя с ID {userId}")
    public Response getUser(int userId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users/" + userId)
                .then()
                .extract().response();
    }

    @Step("Проверка статуса ответа")
    public void validateResponseStatus(Response response) {
        assertEquals(200, response.getStatusCode(), "Неверный статус ответа");
    }

    @Step("Извлечение имени пользователя из ответа")
    public String extractName(Response response) {
        return response.jsonPath().getString("name");
    }

    @Step("Проверка имени пользователя")
    public void validateUserName(String name) {
        assertEquals("Leanne Graham", name, "Ожидалось имя 'Leanne Graham', но было получено другое.");
    }
}
