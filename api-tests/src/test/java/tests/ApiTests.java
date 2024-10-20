package tests;


import dto.response.DTOProductResponse;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApiTests extends BasicApi {

    TestSteps steps = new TestSteps();
    ProductsApi productsApi = new ProductsApi(token);

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
    void postAddNewProduct() {
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
    void putProductById() {
        int productId = 3;
        Response response = productsApi.putProduct(productId, "Updated Product Name", "Electronics",
            15.99, 8);
        steps.verifyStatusCode(response, 200);
        steps.verifyMessage(response, "Product updated successfully");
        //можно было бы сделать ещё проверок если бы метод работал
    }

    @Test
    @DisplayName("[ DELETE /products/{product_id} ] Delete a specific product")
    void deleteById() {
        // по идее сначала нужно создать продукт и из ответа уже взять ID для удаления
        int productId = 3;
        Response response = productsApi.deleteProduct(productId);
        steps.verifyStatusCode(response, 200);
        steps.verifyMessage(response, "Product deleted successfully");
    }

}



