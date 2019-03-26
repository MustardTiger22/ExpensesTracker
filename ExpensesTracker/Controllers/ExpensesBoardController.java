package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Dashboard;
import ExpensesTracker.Models.Expenses;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExpensesBoardController implements Initializable {
    private final Stage thisStage;
    private Dashboard dashboard;
    @FXML private TableView<Expenses> expensesTableView;
    @FXML private TableColumn<Expenses, String> dateColumn;
    @FXML private TableColumn<Expenses, String> descriptionColumn;
    @FXML private TableColumn<Expenses, String> categoryColumn;
    @FXML private TableColumn<Expenses, Double> amountColumn;
    @FXML private Button editBtn;
    @FXML private Button deleteBtn;

    public ExpensesBoardController(Dashboard dashboard) {
        this.dashboard = dashboard;
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ExpensesboardUI.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Expenses board");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<Expenses, String>("date"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Expenses, String>("description"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Expenses, String>("category"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Expenses, Double>("amount"));

        //Table properties
        expensesTableView.setItems(dashboard.getExpensesList().getList());
        expensesTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        expensesTableView.editableProperty().setValue(true);


        //Buttons
        editBtn.setOnAction(e -> {
            dashboard.getExpensesList().saveExpensesToFile();
            System.out.println("Saved to file.");
        });

        deleteBtn.setOnAction(e -> {
            ObservableList<Expenses> allExpenses = expensesTableView.getItems();
            Expenses selectedRow = expensesTableView.getSelectionModel().getSelectedItem();
            System.out.println(selectedRow.getDescription());
            dashboard.getExpensesList().getList().remove(selectedRow);
            allExpenses.remove(selectedRow);

        });
    }
}
