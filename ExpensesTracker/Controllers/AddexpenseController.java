package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Dashboard;
import ExpensesTracker.Models.Expenses;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AddexpenseController implements Initializable {
    private final Stage thisStage;
    private Dashboard dashboard;
    private Date gettedDatePickerDate;
    //FXML
    @FXML private Button saveBtn;
    @FXML private Button cancelBtn;
    @FXML private DatePicker date;
    @FXML private TextField category;
    @FXML private TextField amount;
    @FXML private TextArea description;

    public AddexpenseController(Dashboard dashboard) {
        this.dashboard = dashboard;
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/AddexpenseUI.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.resizableProperty().setValue(false);
            thisStage.setTitle("Add expense");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    private void addToList() {
        try {


            dashboard.getExpensesList().addToList(date, description.getText(), category.getText(), amount.getText());
//            System.out.println(dashboard.getExpenses().getId());
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        saveBtn.setOnAction(e -> {
            gettedDatePickerDate = Date.valueOf(date.getValue());
            addToList();
            thisStage.close();
        });
    }
}
