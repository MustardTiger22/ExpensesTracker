package ExpensesTracker.Models;

import Connectivity.BaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExpensesList{
    private ObservableList<Expenses> expensesList = FXCollections.observableArrayList();
    private BaseConnection test = new BaseConnection();


    public ExpensesList() {
        expensesList.setAll(test.getListOfExpenses());
    }


    public ObservableList<Expenses> getList() {
        return expensesList;
    }

    public void addToList(String datePicker, String description, String category, String price) throws NullPointerException{
        expensesList.add(new Expenses(1,1, datePicker, category, price , description));
    }

    public void setList(ObservableList<Expenses> newList){
        expensesList = newList;
    }

    //return a sum of price every item in the expenses collection
    public Double getSumOfExpensesInGivenMonthAndYear(int month, int year) {
        Double sum = 0.0;
        for(Expenses e : expensesList) {
            if ((e.getDateObject().getMonthValue() - 1) == month && e.getDateObject().getYear() == year) {
                sum += e.getPriceDouble();
            }
        }
        return sum;
    }

    //getMonthValue()-1 because it returns an int value from 1 to 12 and we get in the method a parameter Calendar.getYear() which starts with 0.
    public Double getSumOfTheParticularCategoryInGivenMonthAndYear(String category, int month, int year){
        Double sum = 0.0;
        for(Expenses e : expensesList) {
            if(e.getCategory().contentEquals(category) && (e.getDateObject().getMonthValue()-1) == month && e.getDateObject().getYear() == year) {
                sum += Double.parseDouble(e.getPrice());
            }
        }
        return sum;
    }
}
