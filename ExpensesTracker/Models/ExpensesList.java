package ExpensesTracker.Models;

import Connectivity.BaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExpensesList{
    private Integer originalListHash;
    private ObservableList<Expenses> expensesList = FXCollections.observableArrayList();
    private BaseConnection test = new BaseConnection();


    public ExpensesList() {
        expensesList.setAll(test.getListOfExpenses());
        originalListHash = expensesList.hashCode();
    }


    public ObservableList<Expenses> getList() {
        return expensesList;
    }

    public void addToList(String datePicker, String description, String category, String price) {
        try {
            expensesList.add(new Expenses(1, datePicker, category, price , description));
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setList(ObservableList<Expenses> newList){
        expensesList = newList;
    }


    @Override
    public int hashCode() {
        return Objects.hash(expensesList);
    }

    public void addToList(Expenses expense) {
        try {
            expensesList.add(expense);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
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
