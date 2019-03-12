package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Settings;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    private Settings model = new Settings();

    @FXML private TextField budget;
    @FXML private TextField expenses;
    @FXML private TextField bills;
    @FXML private TextField accounts;
    @FXML private TextField income;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
