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
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChairmanDashboardController implements Initializable {

    @FXML
    private Button logoutButton;
    @FXML
    private Button staffAccountButton;
    @FXML
    private BorderPane viewBorderPane;

    ChangeStage stageChange;
    @FXML
    private Label staffNameLabel;

    @FXML
    private Label hintsLabel;
    @FXML
    private Button salesBtn;
    @FXML
    private Label staffNameLabel1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
    private void staffAccount(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "StaffAccountStage");
        hintsLabel.setVisible(false);
    }

    @FXML
    private void viewSalesReport(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "ViewProductSalesReport");
        hintsLabel.setVisible(false);
    }

    @FXML
    private void viewFinancialReport(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "ViewFinancialReportStage");
        hintsLabel.setVisible(false);
    }

    @FXML
    private void feedback(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "ChairmanFeedbackStage");
        hintsLabel.setVisible(false);
    }

    @FXML
    private void meetingSchedule(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "ChairmanMeetingStage");
        hintsLabel.setVisible(false);
    }

}
