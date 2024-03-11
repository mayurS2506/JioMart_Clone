package ConnectionHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class CreateConnection {
    private static  final String url = "jdbc:mysql://localhost:****/jiomart";
    private static  final String userName = "root";
    private static  final String userPassword = "******";
    public static Connection  createConnection(){
    Connection conn;
    try {
        conn = DriverManager.getConnection(url,  userName, userPassword);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return  conn;
}
}
