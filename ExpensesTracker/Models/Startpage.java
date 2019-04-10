package ExpensesTracker.Models;

import Connectivity.BaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Startpage {
    private BaseConnection database = new BaseConnection();
    private Connection con = database.getConnection();
    private ObservableList<Users> usersList = FXCollections.observableArrayList();

    public Startpage(){
        getUsersFromDatabase();
    }

    public ObservableList<Users> getUsersList() {
        return  usersList;
    }


    public void getUsersFromDatabase(){
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

    public void AddUser(String username, String income, String budget, String bills){
        try{
            String query = "INSERT INTO users(username, income, budget, bills) VALUES('"+username+"','"+income+"','"+budget+"','"+bills+"') ";
            PreparedStatement statement = con.prepareStatement(query, Statement. RETURN_GENERATED_KEYS);
            //To add an object to list I receive id which is autoincremented. That's important to keep a consistency of data in the list and the database.
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            //Here I get the id
            if(rs.next()) {
                //A ResultSet cursor is initially positioned before the first row;
                usersList.add(new Users(rs.getInt(1), username, income, budget, bills));
                System.out.println(rs.getInt(1));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    public BaseConnection getDatabase() {return database;}








}
