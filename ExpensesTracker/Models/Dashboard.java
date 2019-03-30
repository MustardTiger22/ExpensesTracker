package ExpensesTracker.Models;


import java.util.Calendar;
import java.util.Locale;

public class Dashboard {
    private Calendar cal = Calendar.getInstance();
    private String formattedDateString = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.YEAR);
    //There is the expensesList initialization
    private ExpensesList expensesListObj = new ExpensesList();


    public ExpensesList getExpensesListObj(){
        return expensesListObj;
    }
    public String getFormattedDate() {
        return formattedDateString;
    }
    public void setFormattedDateString(int month, int year) {
        cal.set(year, month, 1);
        formattedDateString = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.YEAR);
    }

}
