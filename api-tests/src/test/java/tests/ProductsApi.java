package tests;

import io.restassured.response.Response;
import tests.endpoints.Urls;

public class ProductsApi extends BasicApi {

/*    private ProductsApi(String token) {
        super(token);
    }

    public static ProductsApi productsApi(String token) {
        return new ProductsApi(token);
    }

    public static ProductsApi productsApi() {
        return new ProductsApi(null);
    }*/

    public ProductsApi(String token) {
        super(token);
    }

    public Response getProducts() {
        return getBuilder()
            .get(Urls.PRODUCTS);
    }

    public Response getProductsWithoutAuth() {
        return getBuilderWithoutAuth()
            .get(Urls.PRODUCTS);
    }

    public Response addNewProduct(String name, String category, int price, int discount) {
        return getBuilder()
            .body("""
                {
                  "name": "%s",
                  "category": "%s",
                  "price": %s,
                  "discount": %s
                }
                """.formatted(name, category, price, discount))
            .post(Urls.PRODUCTS);
    }

    public Response getProduct(int id) {
        return  getBuilder()
            .get(Urls.PRODUCTS + "/%s".formatted(id));
    }



}
