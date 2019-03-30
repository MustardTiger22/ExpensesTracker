package ExpensesTracker;

import ExpensesTracker.Controllers.DashboardController;
import ExpensesTracker.Controllers.ExitWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {
    private DashboardController dashboardController = new DashboardController();
    @Override
    public void start(Stage primaryStage) throws Exception{
        dashboardController.showStage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
