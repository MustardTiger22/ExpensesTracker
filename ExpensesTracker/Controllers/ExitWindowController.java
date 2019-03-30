package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Dashboard;
import ExpensesTracker.Models.Settings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExitWindowController implements Initializable {
    private final Stage thisStage;
    private Settings settings = new Settings();
    private DashboardController dashboardController;
    private Dashboard dashboard;
    Boolean hasCloseBtnPressed = new Boolean(false);

    @FXML private Button doNotSaveButton;
    @FXML private Button cancelBtn;
    @FXML private Button saveAndExitBtn;

    public ExitWindowController(Dashboard dashboard, DashboardController dashboardController) {
        this.dashboard = dashboard;
        this.dashboardController = dashboardController;
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ExitWindowUI.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.resizableProperty().setValue(false);
            thisStage.setTitle("");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage(){
        thisStage.showAndWait();
    }

    public Boolean getHasCloseBtnPressed() {
        return hasCloseBtnPressed;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        doNotSaveButton.setOnAction(e -> {
            dashboardController.dashboardStage().close();
            thisStage.close();
        });
        cancelBtn.setOnAction(e -> {
            hasCloseBtnPressed = true;
            thisStage.close();
        });
        saveAndExitBtn.setOnAction(e -> {
            settings.saveSettings();
            dashboard.getExpensesListObj().saveExpensesToFile();
            dashboardController.dashboardStage().close();
            thisStage.close();
        });
    }
}
