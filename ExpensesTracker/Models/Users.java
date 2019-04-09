package ExpensesTracker.Models;

import Connectivity.BaseConnection;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Users {
    private Integer id;
    private String username;
    private String budget;
    private String bills;
    private String income;
    private BaseConnection database = new BaseConnection();

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



    public void loadSettings(TextField username, TextField budget, TextField bills, TextField income) {
        username.setText(getUsername());
        budget.setText(getBudget());
        bills.setText(getBills());
        income.setText(getIncome());
    }

    public void loadSettings(Label username, Label budget, Label bills, Label income) {
        username.setText(getUsername());
        budget.setText(getBudget());
        bills.setText(getBills());
        income.setText(getIncome());
    }
}
