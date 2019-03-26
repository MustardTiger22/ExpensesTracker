package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Dashboard;
import ExpensesTracker.Models.Settings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class DashboardController implements Initializable {
    private Dashboard dashboard = new Dashboard();
    private Settings userSettings;
    private final Stage thisStage;
    @FXML private Button showExpensesBoardBtn;
    @FXML private Button addExpenseBtn;
    @FXML private Button settingsBtn;
    @FXML private PieChart recapPieChart;
    @FXML private Label dateValue;
    @FXML private Label username;
    @FXML private Label expenses;
    @FXML private Label bills;
    @FXML private Label income;
    @FXML private Label budget;
    @FXML private Label accounts;


    public DashboardController() {
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/DashboardUI.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.resizableProperty().setValue(false);
            thisStage.setTitle("Dashboard");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage(){
        thisStage.show();
    }

    public void setExpensesLabel() {
        expenses.setText(dashboard.getExpensesList().getSumOfExpenses().toString());
    }

    public void setRecapPieChart() {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Expenses", dashboard.getExpensesList().getSumOfExpenses()),
                        new PieChart.Data("Income", Double.parseDouble(income.getText())),
                        new PieChart.Data("Budget", Double.parseDouble(budget.getText())),
                        new PieChart.Data("Bills", Double.parseDouble(bills.getText())));
        recapPieChart.setData(pieChartData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(userSettings.IfTheFileExists()) {
            userSettings = new Settings();
            userSettings.LoadSettings(username, budget, expenses, bills, income);
            setExpensesLabel();
        }
        dateValue.setText(dashboard.getFormattedDate());
        setRecapPieChart();

        showExpensesBoardBtn.setOnAction(e -> {
            ExpensesBoardController expensesBoardController = new ExpensesBoardController(dashboard);
            expensesBoardController.showStage();
        });
        addExpenseBtn.setOnAction(e -> {
            AddexpenseController addexpenseController = new AddexpenseController(dashboard);
            addexpenseController.showStage();
            if(addexpenseController.getSaveButtonPressed()) {
                setExpensesLabel();
                setRecapPieChart();

            }
        });
        settingsBtn.setOnAction(e -> {
            thisStage.close();
            SettingsController settingsController = new SettingsController(dashboard);
            settingsController.showStage();
        });


    }
}
