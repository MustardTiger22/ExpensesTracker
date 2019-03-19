package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Expensesboard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExpensesBoardController implements Initializable {
    private Expensesboard model;
    @FXML private Button addExpense;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addExpense.setOnAction(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../Views/AddExpenseUI.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Add expense");
                stage.setScene(new Scene(root));
                stage.showAndWait();

            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
