package dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DTOProductRequest {

    private String name;
    private String category;
    private double price;
    private double discount;
}



