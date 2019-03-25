package ExpensesTracker.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.Calendar;
import java.util.Locale;

public class Dashboard {
    private Calendar cal = Calendar.getInstance();
    private String formattedDateString = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.YEAR);
    private ObservableList<Expenses> expensesList = FXCollections.observableArrayList();

    //Methods

    public String getFormattedDate() {
        return formattedDateString;
    }
    public ObservableList<Expenses> getExpensesList() {
        return expensesList;
    }
    public void addToList(String datePicker, String description, String category, String amount) {
        try {
            expensesList.add(new Expenses(datePicker, description, category , amount));
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
