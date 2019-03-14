package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Settings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import ExpensesTracker.Models.Dashboard;


public class DashboardController implements Initializable {
    private Dashboard model = new Dashboard();
    private Settings userSettings;

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
        userSettings = new Settings();
        userSettings.LoadSettings(username, budget, expenses, bills, accounts, income);

        dateValue.setText(model.getFormattedDate());


        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Expenses", Double.parseDouble(expenses.getText())),
                        new PieChart.Data("Income", Double.parseDouble(income.getText())),
                        new PieChart.Data("Budget", Double.parseDouble(budget.getText())),
                        new PieChart.Data("Accounts", Double.parseDouble(accounts.getText())),
                        new PieChart.Data("Bills", Double.parseDouble(bills.getText())));
        recapPieChart.setData(pieChartData);

        dateValue.setText(model.getFormattedDate());
    }
}
