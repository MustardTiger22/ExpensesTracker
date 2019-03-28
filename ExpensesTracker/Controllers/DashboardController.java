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
import java.util.Calendar;
import java.util.ResourceBundle;



public class DashboardController implements Initializable {
    private Dashboard dashboard = new Dashboard();
    private Settings userSettings;
    private final Stage thisStage;
    @FXML private Button showExpensesBoardBtn;
    @FXML private Button addExpenseBtn;
    @FXML private Button settingsBtn;
    @FXML private PieChart recapPieChart;
    @FXML private PieChart categoryPieChart;
    @FXML private Label dateValue;
    @FXML private Label username;
    @FXML private Label expenses;
    @FXML private Label bills;
    @FXML private Label income;
    @FXML private Label budget;
    @FXML private Label accounts;
    @FXML private Label balance;


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

    public void setGUI() {
        int month =  Calendar.getInstance().get(Calendar.MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);

        if(userSettings.IfTheFileExists()) {
            userSettings = new Settings();
            userSettings.LoadSettings(username, budget, bills, income);
            setRecapPieChart();
            setExpensesLabel();
            setBalance();
            setCategoryPieChart(month, year);

        }
        if(dashboard.getExpensesListObj().IfTheFileExists()) {

        }
    }

    public void setExpensesLabel() {
        expenses.setText(dashboard.getExpensesListObj().getSumOfExpenses().toString());
    }

    public void setRecapPieChart() {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Expenses", dashboard.getExpensesListObj().getSumOfExpenses()),
                        new PieChart.Data("Income", Double.parseDouble(income.getText())),
                        new PieChart.Data("Budget", Double.parseDouble(budget.getText())),
                        new PieChart.Data("Bills", Double.parseDouble(bills.getText())));
        recapPieChart.setData(pieChartData);
    }
    public void setCategoryPieChart(int month, int year) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Food", dashboard.getExpensesListObj().getSumOfTheParticularCategoryInGivenMonthAndYear("Food", month, year)),
                new PieChart.Data("Clothes", dashboard.getExpensesListObj().getSumOfTheParticularCategoryInGivenMonthAndYear("Clothes", month, year)),
                new PieChart.Data("Hobby", dashboard.getExpensesListObj().getSumOfTheParticularCategoryInGivenMonthAndYear("Hobby", month, year)),
                new PieChart.Data("Transport", dashboard.getExpensesListObj().getSumOfTheParticularCategoryInGivenMonthAndYear("Transport",  month, year)),
                new PieChart.Data("Health", dashboard.getExpensesListObj().getSumOfTheParticularCategoryInGivenMonthAndYear("Health", month, year)),
                new PieChart.Data("hygiene and chemistry", dashboard.getExpensesListObj().getSumOfTheParticularCategoryInGivenMonthAndYear("Health, hygiene and chemistry", month, year)),
                new PieChart.Data("Other", dashboard.getExpensesListObj().getSumOfTheParticularCategoryInGivenMonthAndYear("Other", month, year)));
        categoryPieChart.setData(pieChartData);
    }

    public void setBalance() {
            Double computedBalance = Double.parseDouble(userSettings.getBudget()) - dashboard.getExpensesListObj().getSumOfExpenses() - Double.parseDouble(userSettings.getBills());
            balance.setText(computedBalance.toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Loads a settings from file and sets the labels properties and piechart
        setGUI();
        System.out.println(Calendar.getInstance().get(Calendar.YEAR));
        dateValue.setText(dashboard.getFormattedDate());
        showExpensesBoardBtn.setOnAction(e -> {
            ExpensesBoardController expensesBoardController = new ExpensesBoardController(dashboard);
            expensesBoardController.showStage();
            if(expensesBoardController.getHasPressedDeleteButton()) {
                setGUI();
            }
        });
        addExpenseBtn.setOnAction(e -> {
            AddexpenseController addexpenseController = new AddexpenseController(dashboard);
            addexpenseController.showStage();
            if(addexpenseController.getSaveButtonPressed()) {
                setGUI();
            }
        });
        settingsBtn.setOnAction(e -> {
            thisStage.close();
            SettingsController settingsController = new SettingsController(dashboard);
            settingsController.showStage();
        });


    }
}
