package ExpensesTracker.Models;

import javafx.scene.control.DatePicker;


public class Expenses {
    private String date;
    private String description;
    private String category;
    private String amount;

    public String getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }
    public String getCategory() {
        return category;
    }
    public String getAmount() {
        return amount;
    }

    public Expenses(String date, String description, String category, String amount) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
    }
    public Expenses() {

    }

}
