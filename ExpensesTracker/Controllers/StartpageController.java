package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Startpage;
import ExpensesTracker.Models.Users;
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
    Startpage startpage = new Startpage();

    @FXML private Button chooseBtn;
    @FXML private Button closeBtn;
    @FXML private Button refreshBtn;
    @FXML private Button deleteBtn;
    @FXML private Button addNewUserBtn;
    @FXML private TableView tableView;
    @FXML private TableColumn<Users, String> usernameCol;
    @FXML private TableColumn<Users, String> incomeCol;
    @FXML private TableColumn<Users, String> budgetCol;
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
        incomeCol.setCellValueFactory(new PropertyValueFactory<Users, String>("income"));
        budgetCol.setCellValueFactory(new PropertyValueFactory<Users, String>("budget"));
        billsCol.setCellValueFactory(new PropertyValueFactory<Users, Double>("bills"));


        //Table properties
        tableView.setItems(startpage.getUsersList());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);




        chooseBtn.setOnAction(e -> {
            ObservableList<Users> allExpenses = tableView.getItems();
            Users selectedUser = (Users)tableView.getSelectionModel().getSelectedItem();
            if(selectedUser != null) {
                DashboardController dashboardController = new DashboardController(selectedUser);
                dashboardController.showStage();
                thisStage.close();
            }

        });
        closeBtn.setOnAction(e -> {

        });
        refreshBtn.setOnAction(e -> {

        });
        deleteBtn.setOnAction(e -> {

        });
        addNewUserBtn.setOnAction(e -> {

        });

    }
}
