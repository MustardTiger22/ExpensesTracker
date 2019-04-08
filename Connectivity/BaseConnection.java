package Connectivity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Stack;

public class BaseConnection {
    private ObservableList<String> statementList = FXCollections.observableArrayList();
    private Connection connection;

    public BaseConnection() {
        getConnection();
    }

    public Connection getConnection() {
        String dbname = "ExpensesApp";
        String username= "root";
        String password= "qwerty123";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionURL = "jdbc:mysql://localhost:3306/"+dbname+"?autoReconnect=true&useSSL=false";
            connection = DriverManager.getConnection(connectionURL, username, password);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void addToBase(String date, String category, String price, String description){
        String sql = "INSERT INTO expensesboard(date, category, price, description) VALUES ('"+date+"','"+category+"','"+price+"','"+description+"')";
        statementList.add(sql);

    }

    public void executeAddToBase(){
        try {
            Statement statement = connection.createStatement();
            for (String sql : statementList) {
                System.out.println(sql);
                statement.executeUpdate(sql);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
