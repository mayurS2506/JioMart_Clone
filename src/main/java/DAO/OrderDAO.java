package DAO;

import DTO.CustomerDto;
import DTO.OrderDto;
import DTO.OrderPayload;

import java.util.List;

public interface OrderDAO {
    OrderDto addProductToOrderProduct(CustomerDto d);
      int PlaceOrder(OrderDto d);
    List<OrderPayload> displayOrder(CustomerDto d);
}
