package ExpensesTracker.Models;

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

    public void addToList(DatePicker datePicker, String description, String category, String amount) {
        try {
            expensesList.add(new Expenses(datePicker, description, category , amount));
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

    public static Boolean IfTheFileExists(){
        new File("./Configuration").mkdirs();
        String filePathString = "Configuration/expensestable.ser";
        File f = new File(filePathString);
        if(f.exists() && !f.isDirectory())
            return true;

        return false;
    }

}
