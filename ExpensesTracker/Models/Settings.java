package ExpensesTracker.Models;

import com.gluonhq.charm.glisten.control.TextField;
import javafx.scene.control.Label;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Settings implements Serializable {
    private String username;
    private String budget;
    private String expenses;
    private String bills;
    private String accounts;
    private String income;

    public Settings(String username, String budget, String expenses, String bills, String accounts, String income) {
        this.username = username;
        this.budget = budget;
        this.expenses = expenses;
        this.bills = bills;
        this.accounts = accounts;
        this.income = income;
    }

    public Settings() {
//        try {
//            FileInputStream fs = new FileInputStream("usersettings.ser");
//            ObjectInputStream is = new ObjectInputStream(fs);
//            Settings oneRestore = (Settings) is.readObject();
//            this.username = oneRestore.getUsername();
//            this.budget = oneRestore.getBudget();
//            this.expenses = oneRestore.getExpenses();
//            this.bills = oneRestore.getBills();
//            this.accounts = oneRestore.getAccounts();
//            this.income = oneRestore.getIncome();
//            is.close();
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public String getUsername() {
        return username;
    }
    public String getBudget() {
        return budget;
    }
    public String getExpenses() {
        return expenses;
    }
    public String getBills() {
        return bills;
    }
    public String getAccounts() {
        return accounts;
    }
    public String getIncome() {
        return income;
    }


    public void SaveSettings() {
        try {
            new File("./Configuration").mkdirs();
            FileOutputStream fs = new FileOutputStream("Configuration/usersettings.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(this);
            os.close();
            System.out.println("Settings saved.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadSettings() {
        try {
            FileInputStream fs = new FileInputStream("Configuration/usersettings.ser");
            ObjectInputStream is = new ObjectInputStream(fs);
            Settings oneRestore = (Settings) is.readObject();
            this.username = oneRestore.getUsername();
            this.budget = oneRestore.getBudget();
            this.expenses = oneRestore.getExpenses();
            this.bills = oneRestore.getBills();
            this.accounts = oneRestore.getAccounts();
            this.income = oneRestore.getIncome();
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadSettings(TextField username, TextField budget, TextField expenses, TextField bills, TextField accounts, TextField income) {
        try {
            FileInputStream fs = new FileInputStream("Configuration/usersettings.ser");
            ObjectInputStream is = new ObjectInputStream(fs);
            Settings oneRestore = (Settings) is.readObject();
                username.setText(oneRestore.getUsername());
                budget.setText(oneRestore.getBudget());
                expenses.setText(oneRestore.getExpenses());
                bills.setText(oneRestore.getBills());
                accounts.setText(oneRestore.getAccounts());
                income.setText(oneRestore.getIncome());
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadSettings(Label username, Label budget, Label expenses, Label bills, Label accounts, Label income) {
        try {
            FileInputStream fs = new FileInputStream("Configuration/usersettings.ser");
            ObjectInputStream is = new ObjectInputStream(fs);
            Settings oneRestore = (Settings) is.readObject();
            username.setText(oneRestore.getUsername());
            budget.setText(oneRestore.getBudget());
            expenses.setText(oneRestore.getExpenses());
            bills.setText(oneRestore.getBills());
            accounts.setText(oneRestore.getAccounts());
            income.setText(oneRestore.getIncome());
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Boolean IfTheFileExists(){
        new File("./Configuration").mkdirs();
        String filePathString = "Configuration/usersettings.ser";
        File f = new File(filePathString);
        if(f.exists() && !f.isDirectory())
            return true;

        return false;
    }
}
