package schema;

import java.util.List;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor(force = true)
@Value
public class getProductsResponse {

    List<Product> Products;
}