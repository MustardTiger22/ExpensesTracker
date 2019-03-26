package ExpensesTracker.Models;

import javafx.scene.control.DatePicker;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Expenses implements Serializable {
    private String date;
    private String description;
    private String category;
    private String price;
    private Date dateObj;

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
    public Expenses() {

    }
    public Expenses(String date, String description, String category, String price) {
        this.price = price;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    public Date getDateObject(){
        try{
            dateObj = new SimpleDateFormat("yyyy-MM-dd").parse(getDate());
            return dateObj;
        }
        catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double getPriceDouble(){
        return Double.parseDouble(getPrice());
    }


}
