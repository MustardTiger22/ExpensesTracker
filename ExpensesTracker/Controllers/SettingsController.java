package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Dashboard;
import ExpensesTracker.Models.Users;
import ExpensesTracker.Models.UsersList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController  implements Initializable {
    private final Stage thisStage;
    private NumericFields numericFields;
    private Users user;
    private UsersList usersList = new UsersList();
    @FXML private TextField username;
    @FXML private TextField budget;
    @FXML private TextField bills;
    @FXML private TextField income;
    @FXML private Button saveBtn;
    @FXML private Button closeBtn;

    public SettingsController(Users user) {
        this.user = user;
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/SettingsUI.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.resizableProperty().setValue(false);
            thisStage.setTitle("Users");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUserSettings() {
        username.setText(user.getUsername());
        budget.setText(user.getBudget());
        bills.setText(user.getBills());
        income.setText(user.getIncome());
    }



    public void showStage(){
        thisStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Force textFields to approve only numeric values
        numericFields.changeTextFieldToNumericField(budget);
        numericFields.changeTextFieldToNumericField(bills);
        numericFields.changeTextFieldToNumericField(income);

        loadUserSettings();

        saveBtn.setOnAction(e -> {
            usersList.updateUser(user, username.getText(), income.getText(), budget.getText(), bills.getText());
            DashboardController dashboardController = new DashboardController(user);
            dashboardController.showStage();
            thisStage.close();
        });
        closeBtn.setOnAction(e -> {
            DashboardController dashboardController = new DashboardController(user);
            dashboardController.showStage();
            thisStage.close();
        });
    }


}
