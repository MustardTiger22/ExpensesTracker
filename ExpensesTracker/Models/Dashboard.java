package ExpensesTracker.Models;

import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Dashboard {
    private Calendar cal = Calendar.getInstance();
    private String formattedDateString = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.YEAR);
    private ObservableList<Expenses> expensesList;



    //Methods

    public String getFormattedDate() {
        return formattedDateString;
    }

    public void addToList(Expenses e) {
        expensesList.add(e);
    }

    public void addToList(DatePicker datePicker, String description, String category, String amount) {
        expensesList.add(new Expenses(datePicker, description, category , amount));
    }

    public Expenses getExpenses(int index) {
        return expensesList.get(index);
    }

    public void deleteFromList(int index) {
        expensesList.remove(index);
    }


}
