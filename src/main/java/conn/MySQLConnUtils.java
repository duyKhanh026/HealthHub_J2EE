package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {

    public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        // Update connection parameters accordingly.
        String hostName = "localhost";
        String dbName = "mytest";
        String userName = "root";
        String password = "";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
            throws SQLException, ClassNotFoundException {
        
        // Updated Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // URL Connection for MySQL
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?serverTimezone=UTC";
        
        // Establish and return connection
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}
