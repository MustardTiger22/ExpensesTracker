package ExpensesTracker.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Dashboard {
    private Calendar cal = Calendar.getInstance();
    private String formattedDateString = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.YEAR);
    private ObservableList<Expenses> expensesList = FXCollections.observableArrayList();


    //Methods

    public String getFormattedDate() {
        return formattedDateString;
    }

//    public void addToList(Expenses e) {
//        if(e == null) {
//            throw new NullPointerException();
//        }
//        else
//        expensesList.add(e);
//    }

    public void addToList(String datePicker, String description, String category, String amount) {
        try {
            expensesList.add(new Expenses(datePicker, description, category , amount));
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Expenses getExpenses(int index) {
        return expensesList.get(index);
    }

    public void deleteFromList(int index) {
        expensesList.remove(index);
    }


}
