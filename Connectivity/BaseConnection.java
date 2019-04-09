package Connectivity;

import ExpensesTracker.Models.Expenses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


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

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE `expensesboard2` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `date` date DEFAULT NULL,\n" +
                    "  `category` varchar(45) COLLATE utf8_polish_ci DEFAULT NULL,\n" +
                    "  `price` double DEFAULT NULL,\n" +
                    "  `description` text COLLATE utf8_polish_ci,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;";
            statement.execute(sql);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void addToBase(String date, String category, String price, String description){
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO expensesboard(date, category, price, description) VALUES ('"+date+"','"+category+"','"+price+"','"+description+"')";
            statement.executeUpdate(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteFromBase(int id){
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM expensesboard WHERE id='"+id+"';";
            statement.executeUpdate(sql);
        }
        catch (Exception e) {
                e.printStackTrace();
        }
    }

    public ObservableList<Expenses> getListOfExpenses(){
        ObservableList<Expenses> expensesList = FXCollections.observableArrayList();
        try{
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM expensesboard";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                expensesList.add(new Expenses(rs.getInt("id"), rs.getString("date"), rs.getString("category"), rs.getString("price"), rs.getString("description")));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return expensesList;

    }
}
