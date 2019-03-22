package ExpensesTracker.Models;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Expensesboard implements Initializable {
    private List<Expenses> listOfExpenses;


    public void addToExpensesList(Expenses ex) {
        listOfExpenses.add(ex);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
