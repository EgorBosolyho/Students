package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private static final String URL = "jdbc:mysql://localhost/student?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

public static Connection getConnetction() throws SQLException {
    return DriverManager.getConnection(URL,USER,PASSWORD);
}

}
