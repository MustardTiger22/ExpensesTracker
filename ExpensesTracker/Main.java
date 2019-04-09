package ExpensesTracker;

import ExpensesTracker.Controllers.StartpageController;
import javafx.application.Application;
import javafx.stage.Stage;
public class Main extends Application {
    private StartpageController startpageController = new StartpageController();
    @Override
    public void start(Stage primaryStage) throws Exception{
        startpageController.showStage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
