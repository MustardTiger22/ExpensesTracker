package ExpensesTracker.Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExpensesList implements Serializable {

    private ObservableList<Expenses> expensesList = FXCollections.observableArrayList();
    public ObservableList<Expenses> getList() {
        return expensesList;
    }

    public ExpensesList() {
        if(IfTheFileExists()) {
            loadExpenseFromFile();
        }
    }
    public ObservableList<Expenses> getExpensesList() {
        return expensesList;
    }


    public void addToList(String datePicker, String description, String category, String price) {
        try {
            expensesList.add(new Expenses(datePicker, description, category , price));
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    public void addToList(Expenses expense) {
        try {
            expensesList.add(expense);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    //Serialize the expensesList to file. Path = "Configuration/expenestable.ser"
    public void saveExpensesToFile() {
        try
        {
            new File("./Configuration").mkdirs();
            FileOutputStream fos = new FileOutputStream("Configuration/expensestable.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<Expenses>(expensesList));
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    //Load from the expensesList file. Path = "Configuration/expenestable.ser"
    public void loadExpenseFromFile() {
        try {
            FileInputStream fis = new FileInputStream("Configuration/expensestable.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Expenses> list = (List<Expenses>) ois.readObject();
            expensesList = FXCollections.observableList(list);
            ois.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Check if the expenseslist already exists
    public static Boolean IfTheFileExists(){
        new File("./Configuration").mkdirs();
        String filePathString = "Configuration/expensestable.ser";
        File f = new File(filePathString);
        if(f.exists() && !f.isDirectory())
            return true;

        return false;
    }
    //return a sum of price every item in the expenses collection
    public Double getSumOfExpenses() {
        Double sum = 0.0;
        for(Expenses e : expensesList) {
            sum +=e.getPriceDouble();
        }
        return sum;
    }


    //getMonthValue()-1 because it returns an int value from 1 to 12 and we get in the method a parameter Calendar.getYear() which starts with 0.
    public Double getSumOfTheParticularCategoryInGivenMonthAndYear(String category, int month, int year){
        Double sum = 0.0;
        for(Expenses e : expensesList) {
            if(e.getCategory().contentEquals(category) && (e.getDateObject().getMonthValue()-1) == month && e.getDateObject().getYear() == year) {
                sum += Double.parseDouble(e.getPrice());
            }
        }
        return sum;
    }
}
