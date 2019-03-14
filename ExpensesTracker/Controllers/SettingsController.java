package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Settings;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
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
            model.LoadSettings(username, budget, expenses, bills, accounts, income);
        }


        saveBtn.setOnAction(e -> {
            model = new Settings(username.getText(), budget.getText(), expenses.getText(), bills.getText(), accounts.getText(), income.getText());
            model.SaveSettings();
            try {
                //Switch scenes
                Stage window = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../Views/DashboardUI.fxml"));
                window.setTitle("Expenses Tracker - Dashboard");
                window.setScene(new Scene(root));
                window.show();
                //Closes the current scene
                Node source = (Node) e.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }


        });
    }


}
