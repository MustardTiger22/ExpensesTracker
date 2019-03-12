package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Settings;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    private Settings model;

    @FXML private TextField budget;
    @FXML private TextField expenses;
    @FXML private TextField bills;
    @FXML private TextField accounts;
    @FXML private TextField income;
    @FXML private Button saveBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if(model.IfTheFileExists()) {
                budget = new TextField(model.getBudget());
                expenses = new TextField(model.getExpenses());
                bills = new TextField(model.getBills());
                accounts = new TextField(model.getAccounts());
                income = new TextField(model.getIncome());
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        saveBtn.setOnAction(e -> {
            model = new Settings(budget.toString(), expenses.toString(), bills.toString(), accounts.toString(), income.toString());
            model.SaveSettings();
        });
    }


}
