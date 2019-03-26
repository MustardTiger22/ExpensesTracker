package ExpensesTracker.Models;

import java.util.Calendar;
import java.util.Locale;

public class Dashboard {
    private Calendar cal = Calendar.getInstance();
    private String formattedDateString = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.YEAR);
    private ExpensesList expensesList = new ExpensesList();

    public ExpensesList getExpensesList() {
        return expensesList;
    }

    public String getFormattedDate() {
        return formattedDateString;
    }

}
