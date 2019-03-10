package ExpensesTracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;
import ExpensesTracker.Model.Dashboard;


public class DashBoardController implements Initializable {
    private Dashboard model = new Dashboard();
    private LocalDate currentDate;

    @FXML private PieChart recapPieChart;
    @FXML private Label dateValue;
    @FXML private Label expensesValue;
    @FXML private Label billsValue;
    @FXML private Label incomeValue;
    @FXML private Label budgetValue;
    @FXML private Label accountsValue;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Expenses", Double.parseDouble(expensesValue.getText())),
                        new PieChart.Data("Bills", Double.parseDouble(billsValue.getText())),
                        new PieChart.Data("Income", Double.parseDouble(incomeValue.getText())),
                        new PieChart.Data("Budget", Double.parseDouble(budgetValue.getText())),
                        new PieChart.Data("Accounts", Double.parseDouble(accountsValue.getText())));
        recapPieChart.setData(pieChartData);

        dateValue.setText(model.getFormattedDate());
    }
}
