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
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;



public class DashboardController implements Initializable {
    private Dashboard dashboard = new Dashboard();
    private Settings userSettings;
    //Current date
    private int month =  Calendar.getInstance().get(Calendar.MONTH);
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    private final Stage thisStage;
    @FXML private Button showExpensesBoardBtn;
    @FXML private Button addExpenseBtn;
    @FXML private Button settingsBtn;
    @FXML private Button nextMonthBtn;
    @FXML private Button previousMonthBtn;
    @FXML private TabPane tabPane;
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
        //Sets header tabpane background color
        tabPane.lookup(".tab-pane .tab-header-area .tab-header-background").setStyle("-fx-background-color: #00796B;");
    }

    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }

    public void setGUI() {

        if(userSettings.IfTheFileExists()) {
            userSettings = new Settings();
            userSettings.LoadSettings(username, budget, bills, income);
            setRecapPieChart(getMonth(), getYear());
            setExpensesLabel(getMonth(), getYear());
            setBalance(getMonth(), getYear());
            setCategoryPieChart(getMonth(), getYear());

        }
    }
    public void setGUI(int month, int year) {
        if(userSettings.IfTheFileExists()) {
            userSettings = new Settings();
            userSettings.LoadSettings(username, budget, bills, income);
            setRecapPieChart(month, year);
            setExpensesLabel(month, year);
            setBalance(month, year);
            setCategoryPieChart(month, year);
        }
    }

    public void setExpensesLabel(int month, int year) {
        expenses.setText(dashboard.getExpensesListObj().getSumOfExpensesInGivenMonthAndYear(month, year).toString());
    }
    public void setRecapPieChart(int month, int year) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Expenses", dashboard.getExpensesListObj().getSumOfExpensesInGivenMonthAndYear(month, year)),
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
                new PieChart.Data("Health, hygiene and chemistry", dashboard.getExpensesListObj().getSumOfTheParticularCategoryInGivenMonthAndYear("Health, hygiene and chemistry", month, year)),
                new PieChart.Data("Other", dashboard.getExpensesListObj().getSumOfTheParticularCategoryInGivenMonthAndYear("Other", month, year)));
        categoryPieChart.setData(pieChartData);
    }
    public void setBalance(int month, int year) {
            //computedBalance = Budget - bills - expenses
            Double computedBalance = Double.parseDouble(userSettings.getBudget()) - dashboard.getExpensesListObj().getSumOfExpensesInGivenMonthAndYear(month, year) - Double.parseDouble(userSettings.getBills());
            balance.setText(computedBalance.toString());
            if(computedBalance > 0) {
                balance.setTextFill(Color.web("#2eb82e"));
            }
            else {
                balance.setTextFill(Color.web("#e62e00"));
            }
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
            SettingsController settingsController = new SettingsController(dashboard);
            settingsController.showStage();
            thisStage.close();
        });

        nextMonthBtn.setOnAction(e -> {
            if(month < 12)
                month++;
            else {
                month = 1;
                year++;
            }
            dashboard.setFormattedDateString(month, year);
            dateValue.setText(dashboard.getFormattedDate());
            setGUI(month, year);
        });

        previousMonthBtn.setOnAction(e -> {
            if(month > 1)
                month--;
            else {
                month = 12;
                year--;
            }
            dashboard.setFormattedDateString(month, year);
            dateValue.setText(dashboard.getFormattedDate());
            setGUI(month, year);
        });


    }
}
