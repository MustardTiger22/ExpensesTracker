package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Users;
import ExpensesTracker.Models.UsersList;
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

public class StartpageController implements Initializable{
    private final Stage thisStage;
    private NumericFields numericFields;
    UsersList usersList = new UsersList();
    @FXML private Button chooseBtn;
    @FXML private Button closeBtn;
    @FXML private Button deleteBtn;
    @FXML private Button addNewUserBtn;
    @FXML private TextField username;
    @FXML private TextField income;
    @FXML private TextField budget;
    @FXML private TextField bills;
    @FXML private Tab usersTabPane;
    @FXML private TabPane tabPane;
    @FXML private TableView<Users> tableView;
    @FXML private TableColumn<Users, String> usernameCol;
    @FXML private TableColumn<Users, Double> incomeCol;
    @FXML private TableColumn<Users, Double> budgetCol;
    @FXML private TableColumn<Users, Double> billsCol;


    public StartpageController() {
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/StartpageUI.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.resizableProperty().setValue(false);
            thisStage.setTitle("Start Page");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage(){
        thisStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameCol.setCellValueFactory(new PropertyValueFactory<Users, String>("username"));
        incomeCol.setCellValueFactory(new PropertyValueFactory<Users, Double>("income"));
        budgetCol.setCellValueFactory(new PropertyValueFactory<Users, Double>("budget"));
        billsCol.setCellValueFactory(new PropertyValueFactory<Users, Double>("bills"));

        numericFields.changeTextFieldToNumericField(income);
        numericFields.changeTextFieldToNumericField(budget);
        numericFields.changeTextFieldToNumericField(bills);


        //Table properties
        tableView.setItems(usersList.getList());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);




        chooseBtn.setOnAction(e -> {
            Users selectedUser = tableView.getSelectionModel().getSelectedItem();
            if(selectedUser != null) {
                DashboardController dashboardController = new DashboardController(selectedUser);
                dashboardController.showStage();
                thisStage.close();
            }

        });
        deleteBtn.setOnAction(e -> {
            ObservableList<Users> allExpenses = tableView.getItems();
            Users selectedUser = tableView.getSelectionModel().getSelectedItem();
            if(selectedUser != null) {
                usersList.deleteUser(selectedUser);
                allExpenses.remove(selectedUser);
            }
        });
        addNewUserBtn.setOnAction(e -> {
            usersList.AddUser(username.getText(), income.getText(), budget.getText(), bills.getText());
            //Switching tab pane to pane with users
            tabPane.getSelectionModel().select(usersTabPane);
            //tableView.refresh();
        });
        closeBtn.setOnAction(e -> {
            thisStage.close();
        });

    }
}
