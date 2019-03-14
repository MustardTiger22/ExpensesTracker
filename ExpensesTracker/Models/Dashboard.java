package ExpensesTracker.Models;

import java.time.LocalDateTime;

public class Dashboard {

    @Deprecated private LocalDateTime datetime;
    private String newstring = LocalDateTime.now().toString();


    public String getFormattedDate() {
        return newstring;
    }
}
