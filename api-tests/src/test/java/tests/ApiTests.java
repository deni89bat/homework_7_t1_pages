package tests;


import dto.request.DTOUserRequest;
import dto.response.DTOProductResponse;
import dto.response.DTOProductsList;
import dto.response.DTOUserCartResponse;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.AuthService;
import services.ProductsService;

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
        DTOUserRequest user = new DTOUserRequest("abracadabra", "abracadabrapassword");
        Response loginResponse = AuthService.loginUser(user);
        steps.verifyCodeAndMessage(loginResponse, 401, "Invalid credentials");
    }

    //Product related operations tests
    @Test
    @DisplayName("[ GET /products ] Get a list of products")
    void getListProducts() {
        ProductsService.getProductsWithoutAuth();
    }

    @Test
    @DisplayName("[ POST /products/{product_id} ] Add a new product")
    void addNewProduct() {
        Response response = ProductsService.addNewProduct();
        steps.verifyCodeAndMessage(response, 201, "Product added successfully");
        //можно было бы сделать ещё проверок если бы метод работал
    }

    @Test
    @DisplayName("[ GET /products/{product_id} ] Get information about a specific product")
    void getProductById() {
        ProductsService.getProductByRandomId();
    }

    @Test
    @DisplayName("[ PUT /products/{product_id} ] Update information about a specific product")
    void updateProductById() {
        Response response = ProductsService.putProduct();
        steps.verifyCodeAndMessage(response, 200, "Product updated successfully");
        //можно было бы сделать ещё проверок если бы метод работал
    }

    @Test
    @DisplayName("[ DELETE /products/{product_id} ] Delete a specific product")
    void deleteProductById() {
        // по идее сначала нужно создать продукт и из ответа уже взять ID для удаления
        // int productId = ProductsService.addNewProduct().getBody().getId();
        int productId = 3;
        Response response = ProductsService.deleteProduct(productId);
        steps.verifyCodeAndMessage(response, 200, "Product deleted successfully");
    }

    //Shopping Cart operations tests
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



