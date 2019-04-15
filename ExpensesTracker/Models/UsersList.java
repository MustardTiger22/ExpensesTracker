package ExpensesTracker.Models;

import ExpensesTracker.Connectivity.BaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsersList {
    private BaseConnection database = new BaseConnection();
    private Connection con = database.getConnection();
    private ObservableList<Users> usersList = FXCollections.observableArrayList();


    public ObservableList<Users> getList() {
        return usersList;
    }
    public UsersList() {
        getUsersFromDatabase();
    }

    //DATABASE QUERIES AND LIST MODIFICATION
    private void getUsersFromDatabase(){
        try{
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM ExpensesApp.users";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                usersList.add(new Users(rs.getInt("id"), rs.getString("username"), rs.getString("income"), rs.getString("budget"), rs.getString("bills")));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(String username, String income, String budget, String bills){
        try{
            String query = "INSERT INTO ExpensesApp.users(username, income, budget, bills) VALUES('"+username+"','"+income+"','"+budget+"','"+bills+"') ";
            //To add an object to a list I receive the id which is auto_increment. That's important to keep a consistency of data in the list and the database.
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            //Here I get the id
            ResultSet rs = statement.getGeneratedKeys();
            //A ResultSet cursor is initially positioned before the first row;
            if(rs.next()) {
                usersList.add(new Users(rs.getInt(1), username, income, budget, bills));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Users user){
        try{
            Statement statement = con.createStatement();
            String query = "DELETE FROM ExpensesApp.users WHERE id='"+user.getId()+"';";
            statement.executeUpdate(query);
            usersList.remove(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Users user, String username, String income, String budget, String bills) {
        try{
            Statement statement = con.createStatement();
            String query = "UPDATE ExpensesApp.users set username='"+username+"', income='"+income+"', budget='"+budget
                    +"', bills='"+bills+"' WHERE id='"+user.getId()+"';";
            statement.executeUpdate(query);
            user.setUsername(username);
            user.setIncome(income);
            user.setBudget(budget);
            user.setBills(bills);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
