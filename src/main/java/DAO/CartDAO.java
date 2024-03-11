package DAO;

import DTO.CartDto;
import DTO.PayloadClass;

import java.util.List;

public interface CartDAO extends Operation {
    public  int addProductToCart(CartDto c);
    public  int removeProductFromCart(CartDto c);

    public List<PayloadClass> displayCartProduct( );
}
