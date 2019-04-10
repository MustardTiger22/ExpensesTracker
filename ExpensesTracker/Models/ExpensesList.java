package ExpensesTracker.Models;

import ExpensesTracker.Connectivity.BaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExpensesList{
    private Users user;
    private ObservableList<Expenses> expensesList = FXCollections.observableArrayList();
    private BaseConnection database = new BaseConnection();
    private Connection con = database.getConnection();
    public ExpensesList(Users user) {
        this.user = user;
        loadListFromDatabase();
    }


    public ObservableList<Expenses> getList() {
        return expensesList;
    }

    private void loadListFromDatabase() {
        try {
            String query = "SELECT * FROM expensesboard WHERE userId='"+ user.getId() +"' ";
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                expensesList.add(new Expenses(rs.getInt("id"), rs.getInt("userId"), rs.getString("date"), rs.getString("category"), rs.getString("price"), rs.getString("description")));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addToBaseAndList(String date, String category, String price, String description) {
        try {
            String query = "INSERT INTO expensesboard(date, category, price, description, userId) VALUES ('" + date + "','" + category + "','" + price + "','" + description + "','"+ user.getId() +"');";
            //To add an object to a list I receive the id which is auto_increment. That's important to keep a consistency of data in the list and the database.
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            //Here I get the id
            ResultSet rs = statement.getGeneratedKeys();
            //A ResultSet cursor is initially positioned before the first row;
            if(rs.next()) {
                expensesList.add(new Expenses(rs.getInt(1), user.getId(), date, category, price, description));
                System.out.println(rs.getInt(1));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFromBaseAndList(Expenses expense) {
        try {
            Statement statement = con.createStatement();
            String sql = "DELETE FROM expensesboard WHERE id='" + expense.getId() + "' and userId='" + expense.getUserId() + "';";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //return a sum of price every item in the expenses collection
    public Double getSumOfExpensesInGivenMonthAndYear(int month, int year) {
        Double sum = 0.0;
        for(Expenses e : expensesList) {
            if ((e.getDateObject().getMonthValue() - 1) == month && e.getDateObject().getYear() == year) {
                sum += e.getPriceDouble();
            }
        }
        return sum;
    }

    //getMonthValue()-1 because it returns an int value from 1 to 12 and we get in the method a parameter Calendar.getYear() which starts with 0.
    public Double getSumOfTheParticularCategoryInGivenMonthAndYear(String category, int month, int year){
        Double sum = 0.0;
        for(Expenses e : expensesList) {
            if(e.getCategory().contentEquals(category) && (e.getDateObject().getMonthValue()-1) == month && e.getDateObject().getYear() == year) {
                sum += Double.parseDouble(e.getPrice());
            }
        }
        return sum;
    }
}
