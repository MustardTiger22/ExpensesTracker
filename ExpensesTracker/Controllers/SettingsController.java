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
    @FXML private TextField username;
    @FXML private TextField budget;
    @FXML private TextField expenses;
    @FXML private TextField bills;
    @FXML private TextField accounts;
    @FXML private TextField income;
    @FXML private Button saveBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(model.IfTheFileExists()) {
            model = new Settings();
            model.LoadSettings();
            username.setText(model.getUsername());
            budget.setText(model.getBudget());
            expenses.setText(model.getExpenses());
            bills.setText(model.getBills());
            accounts.setText(model.getAccounts());
            income.setText(model.getIncome());
        }


        saveBtn.setOnAction(e -> {
            model = new Settings(username.getText(), budget.getText(), expenses.getText(), bills.getText(), accounts.getText(), income.getText());
            model.SaveSettings();
        });
    }


}
