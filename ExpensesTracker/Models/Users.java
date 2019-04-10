package ExpensesTracker.Models;

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

    public void setUsername(String username) {
        this.username = username;
    }
    public void setBudget(String budget) {
        this.budget = budget;
    }
    public void setIncome(String income) {
        this.income = income;
    }
    public void setBills(String bills) {
        this.bills = bills;
    }



}
