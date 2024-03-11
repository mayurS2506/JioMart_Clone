package DAO;

import ConnectionHelper.CreateConnection;
import DTO.ProductsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImplementation extends GenralProperty implements ProductDAO {
    private  static Connection conn = CreateConnection.createConnection();
    private final static String  addProductQuery = "INSERT INTO products VALUES ( ?,?,?,?,?,?,?,?);";
    private final static String  setDiscountedPrice = "update products set DiscountedPrice = ? where  product_id=?;";
    private  final  static String  deleteProduct  = "delete  from products where product_id = ?;";
    @Override
    public int addProduct(ProductsDto p) {
        try {
            PreparedStatement pstmt =conn.prepareStatement(addProductQuery);
                pstmt.setString(1,p.getpName());
                pstmt.setString(2, p.getpBrand());
                pstmt.setDouble(3, p.getpPrice());
                pstmt.setDouble(4, p.getpDiscountPrice());
                pstmt.setString(5, p.getpCategory());
                pstmt.setString(6,p.getpSubCategories());
                pstmt.setString(7, p.getPqty());
                pstmt.setInt(8,0);
                int count =  pstmt.executeUpdate();
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int updateProduct(ProductsDto p) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(setDiscountedPrice);
            pstmt.setDouble(1,p.getpDiscountPrice());
            pstmt.setInt(2,p.getPid());
          int count =   pstmt.executeUpdate();
          return  count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int deleteProduct(ProductsDto p) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(deleteProduct);
            pstmt.setInt(1, p.getPid());
            int count =  pstmt.executeUpdate();
            return  count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
