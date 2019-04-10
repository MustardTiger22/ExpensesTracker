package ExpensesTracker.Models;

import Connectivity.BaseConnection;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Users {
    private Integer id;
    private String username;
    private String budget;
    private String bills;
    private String income;


    public Users(Integer id, String username, String budget, String bills, String income) {
        this.id = id;
        this.username = username;
        this.budget = budget;
        this.bills = bills;
        this.income = income;
    }

    public Users() {
    }

    public Integer getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getBudget() {
        return budget;
    }
    public String getBills() {
        return bills;
    }
    public String getIncome() {
        return income;
    }



}
