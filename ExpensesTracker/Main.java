package ExpensesTracker;

import ExpensesTracker.Controllers.DashboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DashboardController dashboardController = new DashboardController();
        dashboardController.showStage();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
