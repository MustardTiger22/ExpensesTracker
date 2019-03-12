package ExpensesTracker.Models;

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

    public void SaveSettings() {
        try {
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

    public static Boolean IfTheFileExists(){
        try {
            final Path path = Files.createTempFile("Configuration/usersettings", ".ser");
            if(Files.exists(path))
                return true;
            else
                return false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
