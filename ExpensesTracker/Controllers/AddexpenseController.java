package ExpensesTracker.Controllers;

import ExpensesTracker.Models.Expenses;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddexpenseController implements Initializable {
    @FXML private Button saveBtn;
    @FXML private Button cancelBtn;
    @FXML private DatePicker date;
    @FXML private TextField category;
    @FXML private TextField amount;
    @FXML private TextArea description;


    public void showStage() {
        try {
            //Switch scenes
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            Parent root = FXMLLoader.load(getClass().getResource("../Views/AddexpenseUI.fxml"));
            saveBtn.setOnAction(e -> {
                window.close();
            window.setTitle("Expenses Tracker - board");
            window.setScene(new Scene(root));

            window.showAndWait();

            });


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
