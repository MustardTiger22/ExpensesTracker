package ExpensesTracker.Models;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.*;
import java.util.Set;


public class Settings implements Serializable {
    private String username;
    private String budget;
    private String bills;
    private String income;

    public Settings(String username, String budget, String bills, String income) {
        this.username = username;
        this.budget = budget;
        this.bills = bills;
        this.income = income;
    }

    public Settings() {
        if(IfTheFileExists()) {
            try {
                FileInputStream fs = new FileInputStream("Configuration/usersettings.ser");
                ObjectInputStream is = new ObjectInputStream(fs);
                Settings oneRestore = (Settings) is.readObject();
                this.username = oneRestore.getUsername();
                this.budget = oneRestore.getBudget();
                this.bills = oneRestore.getBills();
                this.income = oneRestore.getIncome();
                is.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getUsername() {
        return username;
    }
    public String getBudget() {
        return budget;
    }
    public String getBills() {
        return bills;
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
            this.bills = oneRestore.getBills();
            this.income = oneRestore.getIncome();
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadSettings(TextField username, TextField budget, TextField bills, TextField income) {
        username.setText(getUsername());
        budget.setText(getBudget());
        bills.setText(getBills());
        income.setText(getIncome());
    }

    public Settings LoadSettingsObject() {
        Settings se = new Settings();
        return se;
    }

    public void LoadSettings(Label username, Label budget, Label bills,  Label income) {
        username.setText(getUsername());
        budget.setText(getBudget());
        bills.setText(getBills());
        income.setText(getIncome());
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
