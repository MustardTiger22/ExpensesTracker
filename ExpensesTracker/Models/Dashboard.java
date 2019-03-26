package ExpensesTracker.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.Calendar;
import java.util.Locale;

public class Dashboard {
//    private ObservableList<PieChart.Data> pieChartData;
    private Calendar cal = Calendar.getInstance();
    private String formattedDateString = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.YEAR);

    //There is the expensesList initialization
    private ExpensesList expensesList = new ExpensesList();

    public ExpensesList getExpensesList() {
        return expensesList;
    }

    public String getFormattedDate() {
        return formattedDateString;
    }

//    public void setRecapPieChartData(Double expenses, Double income, Double budget, Double bills) {
//        ObservableList<PieChart.Data> setRecapPieChartData =
//                FXCollections.observableArrayList(
//                        new PieChart.Data("Expenses", expenses),
//                        new PieChart.Data("Income", income),
//                        new PieChart.Data("Budget", budget),
//                        new PieChart.Data("Bills", bills))
//                ;}
//
//    public ObservableList<PieChart.Data> getRecapPieChartData() {
//        return pieChartData;
//    }


}
