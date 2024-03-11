package DAO;

import ConnectionHelper.CreateConnection;
import DTO.ProductsDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public abstract  class GenralProperty implements Operation {
    private  static Connection conn = CreateConnection.createConnection();
    private  static String  dispayProduct  = "select * from products;";

    @Override

    public  List<ProductsDto> displayProduct() {
        ArrayList<ProductsDto> productDTOArrayList = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(dispayProduct);
            ResultSet rs=  pstmt.executeQuery();
            ProductsDto p = null;
            while (rs.next()){
                p=new ProductsDto();
                p.setpName(rs.getString(1));
                p.setpBrand(rs.getString(2));
                p.setpPrice(rs.getDouble(3));
                p.setpDiscountPrice(rs.getDouble(4));
                p.setpCategory(rs.getString(5));
                p.setpSubCategories(rs.getString(6));
                p.setPqty(rs.getString(7));
                p.setPid(rs.getInt(8));
                productDTOArrayList.add(p);
            }
            return productDTOArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
