package ExpensesTracker.Models;

import javafx.scene.control.DatePicker;

public class Expenses {
    private DatePicker date;
    private String description;
    private String category;
    private String amount;
    private static int amountOfExpenses = 0;

    public DatePicker getDate() {
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
    public static int getAmountOfExpenses() {
        return amountOfExpenses;
    }

    Expenses(DatePicker date, String description, String category, String amount) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
        amountOfExpenses++;
    }

}
