package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Settings;
import ExpensesTracker.Models.Dashboard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class DashboardController implements Initializable {
    private Dashboard model = new Dashboard();
    private Settings userSettings;

    @FXML private Button showExpensesBoardBtn;
    @FXML private Button addExpenseBtn;
    @FXML private PieChart recapPieChart;
    @FXML private Label dateValue;
    @FXML private Label username;
    @FXML private Label expenses;
    @FXML private Label bills;
    @FXML private Label income;
    @FXML private Label budget;
    @FXML private Label accounts;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(userSettings.IfTheFileExists()) {
            userSettings = new Settings();
            userSettings.LoadSettings(username, budget, expenses, bills, accounts, income);
        }
        dateValue.setText(model.getFormattedDate());

        //Buttons
        showExpensesBoardBtn.setOnAction(e -> {
            try {
                //Switch scenes
                Stage window = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../Views/ExpensesboardUI.fxml"));
                window.setTitle("Expenses Tracker - board");
                window.setScene(new Scene(root));
                window.show();
                Node source = (Node) e.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        addExpenseBtn.setOnAction(e -> {
            AddexpenseController addex = new AddexpenseController();
            addex.showStage();
        });


        //Pie chart
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Expenses", Double.parseDouble(expenses.getText())),
                        new PieChart.Data("Income", Double.parseDouble(income.getText())),
                        new PieChart.Data("Budget", Double.parseDouble(budget.getText())),
                        new PieChart.Data("Accounts", Double.parseDouble(accounts.getText())),
                        new PieChart.Data("Bills", Double.parseDouble(bills.getText())));
        recapPieChart.setData(pieChartData);


    }
}
