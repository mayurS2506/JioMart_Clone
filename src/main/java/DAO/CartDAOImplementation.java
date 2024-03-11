package DAO;

import ConnectionHelper.CreateConnection;
import DTO.CartDto;
import DTO.PayloadClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImplementation extends  GenralProperty implements CartDAO {
    private  static  final Connection conn =CreateConnection.createConnection();
    private static  final String addProductQuery = "insert into cart values (?,?,?);";
    private  static  final  String addSpecific  = "update cart set product_qty = ? where cart_id = ?;";
    private  static  final  String removeProductFromCart = "delete from cart where product_id = ?;";

    private  static  final  String checkPresence = " select cart_id, product_qty from cart where product_id=? ;";
    private  static  final  String displayAllCartProduct = "select cart_id ,product_qty,product_id,  ProductName,Brand , Price , DiscountedPrice,Quantity  FROM products NATURAL JOIN cart;";

    @Override
    public int addProductToCart(CartDto c) {

        try {
            PreparedStatement pstmt1 = conn.prepareStatement(checkPresence);
            pstmt1.setInt(1 , c.getProduct_id());
            ResultSet rs1= pstmt1.executeQuery();
            if (rs1.next()){
                c.setCart_id(rs1.getInt(1));
               int oldQty  = rs1.getInt(2);
               PreparedStatement pstm2 = conn.prepareStatement(addSpecific);
               pstm2.setInt(1,oldQty+c.getProduct_qty());
               pstm2.setInt(2,c.getCart_id());
               return  pstm2.executeUpdate();
            }else {
                PreparedStatement pstmt = conn.prepareStatement(addProductQuery);
                pstmt.setInt(1, 0);
                pstmt.setInt(2,  c.getProduct_qty());
                pstmt.setInt(3, c.getProduct_id());
                return pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int removeProductFromCart(CartDto c) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(removeProductFromCart);
            pstmt.setInt(1,c.getProduct_id());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<PayloadClass> displayCartProduct( ) {
        ArrayList<PayloadClass> payloadArrayLoadArrayList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(displayAllCartProduct);
            while (rs.next()){
                PayloadClass payloadClass = new PayloadClass();
                payloadClass.setProductId(rs.getInt(3));
                payloadClass.setCartId(rs.getInt(1));
                payloadClass.setCartQty(rs.getInt(2));
                payloadClass.setProductName(rs.getString(4));
                payloadClass.setBrand(rs.getString(5));
                payloadClass.setPrice(rs.getDouble(6));
                payloadClass.setDiscountedPrice(rs.getDouble(7));
                payloadClass.setProductQty(rs.getString(8));
                payloadArrayLoadArrayList.add(payloadClass);

            }
            return  payloadArrayLoadArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
