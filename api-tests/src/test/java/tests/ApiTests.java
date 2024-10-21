package tests;


import assertions.BasicAssert;
import dto.request.DTOUserRequest;
import dto.response.DTOProductResponse;
import dto.response.DTOUserCartResponse;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.AuthService;

public class ApiTests extends BasicApi {

    TestSteps steps = new TestSteps();
    ProductsApi productsApi = new ProductsApi(token);
    CartApi cartApi = new CartApi(token);

    //User registration and login tests
    @Test
    @DisplayName("[ POST /register ] Register a new user")
    void positiveRegisterNewUserTest() {
        AuthService.createAndRegisterNewUser();
    }

    @Test
    @DisplayName("[ POST /register Negative - 'User already exists' ] Register a new user")
    void negativeRegisterLoginExistsTest() {
        DTOUserRequest user = AuthService.createAndRegisterNewUser();
        // Повторная попытка регистрации с тем же пользователем
        AuthService.registerUserAndVerify(user, 400, "User already exists");
    }

    @Test
    @DisplayName("[ POST /register Negative - 'No password' ] Register a new user")
    void negativeRegisterNoPasswordTest() {
        DTOUserRequest user = DTOUserRequest.getUserWithoutPassword();
        AuthService.registerUserAndVerify(user, 400, "Username and password are required");
    }

    @Test
    @DisplayName("[ POST /register Negative - 'No username' ] Register a new user")
    void negativeRegisterNoUserNameTest() {
        DTOUserRequest user = DTOUserRequest.getUserWithoutUsername();
        AuthService.registerUserAndVerify(user, 400, "Username and password are required");
    }

    @Test
    @DisplayName("[ POST /login ] Log in with username and password")
    void positiveNewUserAuthTest() {
        DTOUserRequest user = AuthService.createAndRegisterNewUser();
        Response loginResponse = AuthService.loginUser(user);
        AuthService.verifyLoginResponse(loginResponse);
    }

    @Test
    @DisplayName("[ POST /login Negative - 'Invalid credentials' ] Log in with wrong username and password")
    void negativeUserAuthTest() {
        Response loginResponse = AuthApi.loginUser("abracadabra", "abracadabrapassword");
        steps.verifyCodeAndMessage(loginResponse, 401, "Invalid credentials");
    }

    //Product related operations tests
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

    @Test
    @DisplayName("[ GET /cart ] Get the user's shopping cart")
    void getUserCart() {
        Response response = cartApi.getUserCart();
        DTOUserCartResponse cart = response.as(new TypeRef<DTOUserCartResponse>() {
        });
        System.out.println("Получили дтошку " + cart.toString());
        steps.verifyStatusCode(response, 200);
    }

    @Test
    @DisplayName("[ POST /cart ] Add a product to the user's shopping cart")
    void addProductToCart() {
        Response response = cartApi.addProductToCart(4, 2);
        steps.verifyStatusCode(response, 201);
    }


}



