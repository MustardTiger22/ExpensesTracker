package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Dashboard;
import ExpensesTracker.Models.Settings;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    private final Stage thisStage;
    private Settings settings;
    Dashboard dashboard;
    @FXML private TextField username;
    @FXML private TextField budget;
    @FXML private TextField expenses;
    @FXML private TextField bills;
    @FXML private TextField income;
    @FXML private Button saveBtn;

    public SettingsController(Dashboard dashboard) {
        this.dashboard = dashboard;
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/SettingsUI.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.resizableProperty().setValue(false);
            thisStage.setTitle("Settings");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage(){
        thisStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(settings.IfTheFileExists()) {
            settings = new Settings();
            settings.LoadSettings(username, budget, expenses, bills, income);
        }

        saveBtn.setOnAction(e -> {
            settings = new Settings(username.getText(), budget.getText(), expenses.getText(), bills.getText(), income.getText());
            settings.SaveSettings();
            //dashboard.setPieChartData(budget.getText(), expenses.getText(), bills.getText(), income.getText());
            thisStage.close();
            DashboardController dashboardController = new DashboardController();
            dashboardController.showStage();


        });
    }


}
