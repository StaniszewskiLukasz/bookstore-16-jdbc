package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookstoreConnectionManager {
    private static final String url = "jdbc:mysql://localhost:3306/bookstore16jdbc?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "!352Mondeo";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }
}
