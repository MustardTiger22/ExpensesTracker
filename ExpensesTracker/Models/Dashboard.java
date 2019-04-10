package ExpensesTracker.Models;
import java.util.Calendar;
import java.util.Locale;

public class Dashboard {
    //There is the expensesList initialization
    private ExpensesList listOfExpenses;
    private Users user;
    private Calendar cal = Calendar.getInstance();
    private String formattedDateString = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.YEAR);

    public Dashboard(Users user) {
        this.user = user;
        listOfExpenses = new ExpensesList(user);
    }
    public ExpensesList getListOfExpenses(){
        return listOfExpenses; }
    public String getFormattedDate() {
        return formattedDateString;
    }
    public void setFormattedDateString(int month, int year) {
        cal.set(year, month, 1);
        formattedDateString = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.YEAR);
    }

}
