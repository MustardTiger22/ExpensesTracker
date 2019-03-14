package ExpensesTracker.Models;

import java.time.format.DateTimeFormatter;

public class Expenses {
    private DateTimeFormatter date;
    private String description;
    private String category;
    private String amount;

    public DateTimeFormatter getDate() {
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

    Expenses(DateTimeFormatter date, String description, String category, String amount) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
    }
}
