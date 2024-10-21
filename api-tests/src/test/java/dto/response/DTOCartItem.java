package dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOCartItem {

    private int id;
    private String name;
    private String category;
    private double price;
    private double discount;
    private int quantity;

}