package DAO;

import ConnectionHelper.CreateConnection;
import DTO.CustomerDto;
import DTO.OrderDto;
import DTO.OrderPayload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderImplementation extends CartDAOImplementation implements  OrderDAO{
   private Connection conn = CreateConnection.createConnection();
   private final  String queryforCustID = "select cust_id from customer where cust_contact_no = ? ;";
    private final  String fetchFromCart = "select * from cart ;";
    private final  String displayOrderQuery = "SELECT * FROM orderinfo NATURAL JOIN customer where cust_contact_no=? ;";
    private final  String refreshCart =  "TRUNCATE TABLE cart;";

    private final  String insertIntoOrder = "INSERT INTO orderinfo VALUES (?,?,?,?) ;";
    private final  String insertIntoOrderProduct = "INSERT INTO order_product VALUES (?,?,?,?) ;";
    private final  String getTotalQuery = "SELECT product_qty , DiscountedPrice  FROM order_product NATURAL JOIN cart NATURAL JOIN products WHERE customer_id = ?;  ";

      public OrderDto addProductToOrderProduct(CustomerDto d){
          try {
              PreparedStatement pstmt = conn.prepareStatement(queryforCustID);
              pstmt.setString(1,d.getCust_contact_no());
           ResultSet rs =  pstmt.executeQuery();
           if (rs.next()){
               int customerId = rs.getInt(1);
               PreparedStatement pstmt3 = conn.prepareStatement(fetchFromCart);
               ResultSet rs1 =  pstmt3.executeQuery();
               PreparedStatement pstmt2 = conn.prepareStatement(insertIntoOrderProduct);
              while (rs1.next()){
                pstmt2.setInt(1,0);
                pstmt2.setInt(2,customerId);
                pstmt2.setInt(3,rs1.getInt(2));
                pstmt2.setInt(4,rs1.getInt(3));
                pstmt2.addBatch();
              }
             int [] rscount =   pstmt2.executeBatch();
              if (rscount.length != 0){
                  OrderDto d1 = new OrderDto();
                  d1.setCustomer_id(customerId);
                  return  d1;
              }else {
                  return  null ;
              }
           }else{
               return  null;
           }
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }

      }
    @Override
    public int PlaceOrder(OrderDto d) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertIntoOrder);
            pstmt.setInt(1,0);
            pstmt.setInt(2,d.getCustomer_id());
            pstmt.setDouble(3, d.getAmount());
            pstmt.setString(4,d.getPayment_mode());
          int count =   pstmt.executeUpdate();
            PreparedStatement pstmt1 = conn.prepareStatement(refreshCart);
           pstmt1.executeUpdate();
          return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public OrderDto getTotal(OrderDto d) {
          double total = 0;
        int id = d.getCustomer_id();
        try {
            PreparedStatement pstmt1 = conn.prepareStatement(getTotalQuery);
            pstmt1.setInt(1,id);
            ResultSet rs = pstmt1.executeQuery();
            while (rs.next()){
                total +=rs.getInt(1)*rs.getDouble(2);
            }
            d.setAmount(total);
            return d;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderPayload> displayOrder(CustomerDto d){
        PreparedStatement pstmt = null;
        ArrayList<OrderPayload> orderInfo = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(displayOrderQuery);
            pstmt.setString(1,d.getCust_contact_no());
            ResultSet rs =  pstmt.executeQuery();
          while (rs.next()){
              OrderPayload op = new OrderPayload();
              op.setOrder_id(rs.getInt(1));
              op.setAmount(rs.getDouble(3));
              op.setPayment_mode(rs.getString(4));
              op.setCust_name(rs.getString(6));
              op.setCust_contact_no(rs.getString(7));
              op.setCust_address(rs.getString(8));
              orderInfo.add(op);
          }
          return  orderInfo;

        } catch (SQLException e ) {
           return  null;
        }catch (NullPointerException n){
            return null;
        }

    }
}
