package ExpensesTracker.Models;

import java.io.Serializable;
import java.time.LocalDate;


public class Expenses implements Serializable {
    private String date;
    private String description;
    private String category;
    private String price;
    private LocalDate dateObj;

    public Expenses() {

    }
    public Expenses(String date, String description, String category, String price) {
        this.price = price;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    public String getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }
    public String getCategory() {
        return category;
    }
    public String getPrice() {
        return price;
    }


    public LocalDate getDateObject(){
        try{
            return LocalDate.parse(getDate());
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double getPriceDouble(){
        return Double.parseDouble(getPrice());
    }


}
