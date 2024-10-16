package tests.dto.request;
//можно добавить ломбоковский билдер @Builder и @Getter
public class DTOProduct {

    String name;

    String category;

    int id;

    int price;

    int discount;

    public DTOProduct(String name, String category, int id, int price, int discount) {
        this.name = name;
        this.category = category;
        this.id = id;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }
}
