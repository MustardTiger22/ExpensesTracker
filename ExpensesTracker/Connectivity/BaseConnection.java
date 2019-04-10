package ExpensesTracker.Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;


public class BaseConnection {
    private Connection connection;

    public BaseConnection() {
    }

    public Connection getConnection() {
        String dbname = "ExpensesApp";
        String username = "root";
        String password = "qwerty123";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionURL = "jdbc:mysql://localhost:3306/" + dbname + "?autoReconnect=true&useSSL=false";
            connection = DriverManager.getConnection(connectionURL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}