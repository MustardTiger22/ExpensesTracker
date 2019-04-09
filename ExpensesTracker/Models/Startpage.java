package ExpensesTracker.Models;

import Connectivity.BaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Startpage {
    private BaseConnection database = new BaseConnection();
    private Connection con = database.getConnection();
    private ObservableList<Users> usersList = FXCollections.observableArrayList();

    public Startpage(){
        try{
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM users";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                usersList.add(new Users(rs.getInt("id"), rs.getString("username"), rs.getString("income"), rs.getString("budget"), rs.getString("bills")));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Users> getUsersList() {
        return  usersList;
    }

    public void AddUser(String username, String income, String budget, String bills) throws Exception{
        Statement statement = con.createStatement();
        String sql = "INSERT INTO users(username, income, budget, bills) VALUES('"+username+"','"+income+"','"+budget+"','"+bills+"')";
    }



    public BaseConnection getDatabase() {return database;}








}
