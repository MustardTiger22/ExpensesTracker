package ExpensesTracker.Models;

import java.util.Calendar;
import java.util.Locale;

public class Dashboard {

    private Calendar cal = Calendar.getInstance();
    private String formattedDateString = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.YEAR);


    public String getFormattedDate() {
        return formattedDateString;
    }
}
