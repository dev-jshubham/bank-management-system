import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionExample {
    public static final String url="jdbc:mysql://localhost:3306/your_database";
    public static final String username="your_username";
    public static final String password="your_password";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
}
