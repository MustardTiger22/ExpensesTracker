package ExpensesTracker.Models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Settings implements Serializable {
    private String budget;
    private String expenses;
    private String bills;
    private String accounts;
    private String income;

    public Settings(String budget, String expenses, String bills, String accounts, String income) {
        this.budget = budget;
        this.expenses = expenses;
        this.bills = bills;
        this.accounts = accounts;
        this.income = income;
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

    }

    public void SaveSettings() {
        try {
            FileOutputStream fs = new FileOutputStream("Configuration/usersettings.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(this);
            os.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Settings LoadSettings() {

        try {
            FileInputStream fs = new FileInputStream("Configuration/usersettings.ser");
            ObjectInputStream is = new ObjectInputStream(fs);
            Settings oneRestore = (Settings) is.readObject();
            is.close();
            return this;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean IfTheFileExists(){

        try {
            final Path path = Files.createTempFile("testFile", ".txt");
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
