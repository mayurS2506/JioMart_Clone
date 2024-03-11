package DTO;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PayloadClass {
    private  int productId;
    private  int cartId;
    private String brand;
    private  double price;
    private  double discountedPrice;
    private  String productQty;
    private  int cartQty;
    private  String productName ;
}
