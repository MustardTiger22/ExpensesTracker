package ExpensesTracker.Model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dashboard {
    @Deprecated private LocalDateTime datetime;
    private String newstring = LocalDateTime.now().toString();

    public String getFormattedDate() {
        return newstring;
    }
}
