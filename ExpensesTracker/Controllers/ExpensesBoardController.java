package ExpensesTracker.Controllers;

import Connectivity.BaseConnection;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ExpensesBoardController implements Initializable {
    private final Stage thisStage;
    private Dashboard dashboard;
    private BaseConnection database;
    @FXML private TableView<Expenses> expensesTableView;
    @FXML private TableColumn<Expenses, LocalDate> dateColumn;
    @FXML private TableColumn<Expenses, String> descriptionColumn;
    @FXML private TableColumn<Expenses, String> categoryColumn;
    @FXML private TableColumn<Expenses, Double> priceColumn;
    @FXML private Button closeBtn;
    @FXML private Button deleteBtn;

    public ExpensesBoardController(Dashboard dashboard, BaseConnection database) {
        this.dashboard = dashboard;
        this.database = database;
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ExpensesboardUI.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Expenses board");
            thisStage.resizableProperty().setValue(false);
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
        dateColumn.setCellValueFactory(new PropertyValueFactory<Expenses, LocalDate>("date"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Expenses, String>("description"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Expenses, String>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Expenses, Double>("price"));

        //Table properties
        expensesTableView.setItems(dashboard.getListOfExpenses().getList());
        expensesTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Buttons
        closeBtn.setOnAction(e -> {
            thisStage.close();
        });

        deleteBtn.setOnAction(e -> {
            ObservableList<Expenses> allExpenses = expensesTableView.getItems();
            Expenses selectedRow = expensesTableView.getSelectionModel().getSelectedItem();
            if(selectedRow != null) {
                database.deleteFromBase(selectedRow.getId());
                dashboard.getListOfExpenses().getList().remove(selectedRow);
                allExpenses.remove(selectedRow);
            }

        });
    }
}
