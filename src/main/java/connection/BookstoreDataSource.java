package connection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class BookstoreDataSource {
    private static BasicDataSource basicDataSource = new BasicDataSource();

    static {
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/bookstore16jdbc?serverTimezone=UTC");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("!352Mondeo");
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(15);
        basicDataSource.setMaxOpenPreparedStatements(100);
        //to jest tworzenie połączenia jak w klasie BookstoreConnectionManager ale
        //z pólą połączeń
    }
    public static Connection getConnection()throws SQLException{
        return basicDataSource.getConnection();
    }
}
