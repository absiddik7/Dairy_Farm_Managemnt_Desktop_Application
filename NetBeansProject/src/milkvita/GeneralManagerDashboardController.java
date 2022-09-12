package milkvita;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GeneralManagerDashboardController implements Initializable {

    @FXML
    private Button logoutBtn;
    @FXML
    private Button hireEmployees;
    @FXML
    private Button buyerInfo;
    @FXML
    private Button orderInfo;
    @FXML
    private Button feedback;
    @FXML
    private Button meetingSchedule;

    @FXML
    private BorderPane viewBorderPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent loginSceneParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene nextScene = new Scene(loginSceneParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextScene);
        window.show();
    }

    @FXML
    private void hireEmployees() throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "GMHiringEmployeeStage");
    }

    @FXML
    private void viewBuyerInfo() throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "GMBuyerInfoStage");
    }

    @FXML
    private void viewOrderInfo() throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "GMOrderInfoStage");
    }

    @FXML
    private void feedback() throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "GMFeedbackStage");
    }

    @FXML
    private void meetingSchedule() throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "GMMeetingStage");
    }

}
