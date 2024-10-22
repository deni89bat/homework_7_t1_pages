package dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DTOUserRequest {

    private String username;
    private String password;

    private static int generateRandomNumber() {
        return Math.abs(new Random().nextInt());
    }

    private static DTOUserRequest createUser(String username, String password) {
        return DTOUserRequest.builder()
            .username(username)
            .password(password)
            .build();
    }

    // Метод для позитивных тестов
    public static DTOUserRequest getRandomUser() {
        return createUser("batTestUser" + generateRandomNumber(), "batTestPass");
    }

    // Метод для негативных тестов (например, без пароля)
    public static DTOUserRequest getUserWithoutPassword() {
        return createUser("batTestUser" + generateRandomNumber(), null);
    }

    // Метод для негативных тестов (например, без имени пользователя)
    public static DTOUserRequest getUserWithoutUsername() {
        return createUser(null, "batTestPass");
    }

    // Метод для негативных тестов (например, пустые данные)
    public static DTOUserRequest getEmptyUser() {
        return createUser(null, null); // Пустой объект
    }
}