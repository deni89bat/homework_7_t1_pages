package tests;


import assertions.BasicAssert;
import dto.request.DTOUserRequest;
import dto.response.DTOProductResponse;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import java.util.List;
import java.util.Random;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApiTests extends BasicApi {

    TestSteps steps = new TestSteps();
    ProductsApi productsApi = new ProductsApi(token);
    Random random = BasicApi.random;

    @Test
    @DisplayName("[ POST /register ] Register a new user")
    void positiveRegisterNewUserTest() {
        int randomNumber = Math.abs(random.nextInt());

        DTOUserRequest user = DTOUserRequest.builder()
            .username("batTestUser" + randomNumber)
            .password("batTestPass")
            .build();

        Response response = AuthApi.registerNewUser(user.getUsername(), user.getPassword());
        steps.verifyStatusCode(response, 201);
        steps.verifyMessage(response, "User registered successfully");
    }

    @Test
    @DisplayName("[ POST /register Negative - 'User already exists' ] Register a new user")
    void negativeRegisterLoginExistsTest() {
        int randomNumber = Math.abs(random.nextInt());

        DTOUserRequest user = DTOUserRequest.builder()
            .username("batTestUser" + randomNumber)
            .password("batTestPass")
            .build();

        Response response = AuthApi.registerNewUser(user.getUsername(), user.getPassword());
        steps.verifyStatusCode(response, 201);
        steps.verifyMessage(response, "User registered successfully");

        Response responseLoginExists = AuthApi.registerNewUser(user.getUsername(),
            user.getPassword());
        steps.verifyStatusCode(responseLoginExists, 400);
        steps.verifyMessage(responseLoginExists, "User already exists");
    }

    @Test
    @DisplayName("[ POST /register Negative - 'No password' ] Register a new user")
    void negativeRegisterNoPasswordTest() {
        int randomNumber = Math.abs(random.nextInt());

        DTOUserRequest user = DTOUserRequest.builder()
            .username("batTestUser" + randomNumber)
            .build();

        Response response = AuthApi.registerNewUser(user.getUsername(), user.getPassword());
        steps.verifyStatusCode(response, 400);
        steps.verifyMessage(response, "Username and password are required");
    }

    @Test
    @DisplayName("[ POST /register Negative - 'No username' ] Register a new user")
    void negativeRegisterNoUserNameTest() {
        DTOUserRequest user = DTOUserRequest.builder()
            .password("batTestUser")
            .build();

        Response response = AuthApi.registerNewUser(user.getUsername(), user.getPassword());
        steps.verifyStatusCode(response, 400);
        steps.verifyMessage(response, "Username and password are required");
    }

    @Test
    @DisplayName("[ POST /login ] Log in with username and password")
    void positiveNewUserAuthTest() {
        int randomNumber = Math.abs(random.nextInt());

        DTOUserRequest user = DTOUserRequest.builder()
            .username("batTestUser" + randomNumber)
            .password("batTestPass")
            .build();
        Response registerNewUser = AuthApi.registerNewUser(user.getUsername(), user.getPassword());
        steps.verifyStatusCode(registerNewUser, 201);
        steps.verifyMessage(registerNewUser, "User registered successfully");

        Response loginResponse = AuthApi.loginUser(user.getUsername(), user.getPassword());
        String token = loginResponse.then().extract().jsonPath().getString("access_token");

        steps.verifyStatusCode(loginResponse, 200);
        BasicAssert.assertThat (loginResponse).responseContainField("access_token");
        Assertions.assertThat(token).isNotNull();

    }
    @Test
    @DisplayName("[ POST /login Negative - 'Invalid credentials' ] Log in with wrong username and password")
    void negativeUserAuthTest() {
        Response loginResponse = AuthApi.loginUser("abracadabra", "abracadabrapassword");
        String token = loginResponse.then().extract().jsonPath().getString("access_token");

        steps.verifyStatusCode(loginResponse, 401);
        steps.verifyMessage(loginResponse,"Invalid credentials");

    }


    @Test
    @DisplayName("[ GET /products ] Get a list of products")
    void getListProducts() {
        Response response = productsApi.getProductsWithoutAuth();
        List<DTOProductResponse> products = response.as(new TypeRef<List<DTOProductResponse>>() {
        });
        steps.verifyStatusCode(response, 200);
        productsApi.verifyProductResponseStructureDTO(response, products);
        productsApi.verifyUniqueId(response, products);
    }

    @Test
    @DisplayName("[ POST /products/{product_id} ] Add a new product")
    void addNewProduct() {
        Response response = productsApi.addNewProduct("testProduct", "Electronics", 666, 7);
        steps.verifyStatusCode(response, 201);
        steps.verifyMessage(response, "Product added successfully");
        //можно было бы сделать ещё проверок если бы метод работал
    }

    @Test
    @DisplayName("[ GET /products/{product_id} ] Get information about a specific product")
    void getProductById() {
        int productId = 3;
        Response response = productsApi.getProduct(productId);

        // Проверяем статус ответа
        steps.verifyStatusCode(response, 200);

        // Преобразуем ответ в объект DTOProduct
        DTOProductResponse product = response.as(new TypeRef<List<DTOProductResponse>>() {
        }).get(0);

        // Проверяем, что ID продукта в ответе совпадает с запрошенным
        productsApi.respIdEqualReqId(response, product, productId);

        // Проверяем структуру ответа
        productsApi.verifyProductResponseStructureDTO(response, product);
    }

    @Test
    @DisplayName("[ PUT /products/{product_id} ] Update information about a specific product")
    void updateProductById() {
        int productId = 3;
        Response response = productsApi.putProduct(productId, "Updated Product Name", "Electronics",
            15.99, 8);
        steps.verifyStatusCode(response, 200);
        steps.verifyMessage(response, "Product updated successfully");
        //можно было бы сделать ещё проверок если бы метод работал
    }

    @Test
    @DisplayName("[ DELETE /products/{product_id} ] Delete a specific product")
    void deleteProductById() {
        // по идее сначала нужно создать продукт и из ответа уже взять ID для удаления
        int productId = 3;
        Response response = productsApi.deleteProduct(productId);
        steps.verifyStatusCode(response, 200);
        steps.verifyMessage(response, "Product deleted successfully");
    }

}



