package tests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import schema.Product;

public class ApiTests extends BasicApi {

    TestSteps steps = new TestSteps();
    ProductsApi productsApi = new ProductsApi(token);

    @Test
    @DisplayName("Get a list of products")
    void getListProducts() {
        Response response = productsApi.getProductsWithoutAuth();

        steps.verifyStatusCode(response, 200);
        productsApi.verifyProductResponseStructure(response);
    }

    @Test
    @DisplayName("Add a new product")
    void postAddNewProduct() {
        Response response = productsApi.addNewProduct("testProduct", "Electronics", 666, 7);
        steps.verifyMessage(response, "Product added successfully");
        steps.verifyStatusCode(response, 201);
        productsApi.verifyProductResponseStructure(response);
    }

    @Test
    @DisplayName("Get information about a specific product")
    void getProductById() {
        int productId = 6;
        Response response = productsApi.getProduct(productId);
        List<Product> products = response.as(new TypeRef<List<Product>>() {
        });

        productsApi.respIdEqualReqId(response, productId);

        steps.verifyStatusCode(response, 200);
        productsApi.verifyProductResponseStructure(response);

    }


}
