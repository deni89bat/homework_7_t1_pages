package dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOUserCartResponse {

    private List<DTOCartItem> cart;
    private double total_price;
    private double total_discount;
}