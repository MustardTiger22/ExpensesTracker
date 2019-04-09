package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Dashboard;
import ExpensesTracker.Models.Settings;
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

public class SettingsController implements Initializable {
    private final Stage thisStage;
    private Settings settings;
    private Dashboard dashboard;
    @FXML private TextField username;
    @FXML private TextField budget;
    @FXML private TextField bills;
    @FXML private TextField income;
    @FXML private Button saveBtn;
    @FXML private Button closeBtn;

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

    private void changeTextFieldToNumericFieldI(TextField textField){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,10}([\\.]\\d{0,4})?")) {
                    textField.setText(oldValue);
                }
            }
        });

    }

    public void showStage(){
        thisStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(settings.ifTheFileExists()) {
            settings = new Settings();
            settings.loadSettings(username, budget, bills, income);
        }
        //Force textFields to approve only numeric values
        changeTextFieldToNumericFieldI(budget);
        changeTextFieldToNumericFieldI(bills);
        changeTextFieldToNumericFieldI(income);

        saveBtn.setOnAction(e -> {
            settings = new Settings(username.getText(), budget.getText(), bills.getText(), income.getText());
            settings.saveSettings();
            DashboardController dashboardController = new DashboardController();
            dashboardController.showStage();
            thisStage.close();
        });
        closeBtn.setOnAction(e -> {
            DashboardController dashboardController = new DashboardController();
            dashboardController.showStage();
            thisStage.close();
        });
    }


}
