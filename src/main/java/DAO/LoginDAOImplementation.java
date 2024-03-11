package DAO;

import ConnectionHelper.CreateConnection;
import DTO.CustomerDto;
import DTO.LoginDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImplementation  implements  LoginDAO{
    private Connection conn = CreateConnection.createConnection();
    private  String createAcc = "insert into customer values (?,?,?,?,?,?);";
    private  String loginQuery = "select cust_password, cust_contact_no from customer where cust_email=? or cust_contact_no = ?; ";
    @Override
    public CustomerDto login(LoginDto d) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(loginQuery);
            pstmt.setString(1,d.getUsername());
            pstmt.setString(2,d.getUsername());
            ResultSet rs =  pstmt.executeQuery();
            if (rs.next()){
                if (d.getPassword().equals(rs.getString(1))){
                    CustomerDto dto = new CustomerDto();
                    dto.setCust_contact_no(rs.getString(2));
                    return dto;
                }else{
                    return null;
                }
            }else{
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean createAccount(CustomerDto c) {
        try {
            PreparedStatement pstmt =  conn.prepareStatement(createAcc);
            pstmt.setInt(1,0);
            pstmt.setString(2, c.getCust_name());
            pstmt.setString(3,c.getCust_contact_no());
            pstmt.setString(4,c.getCust_address());
            pstmt.setString(5,c.getCust_email());
            pstmt.setString(6,c.getCust_pass());
          int count =  pstmt.executeUpdate();
               if(count>0){
                   return true;
               }else {
                   return  false ;
               }
        } catch (SQLException e) {
            return  false;
        }

    }
}
