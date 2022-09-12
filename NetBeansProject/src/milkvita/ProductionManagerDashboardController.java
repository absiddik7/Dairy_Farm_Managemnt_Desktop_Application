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

public class ProductionManagerDashboardController implements Initializable {

    @FXML
    private Button logoutBtn;
    @FXML
    private Button productonRateBtn;
    @FXML
    private Button productDetailsBtn;
    @FXML
    private Button orderInfoBtn;
    @FXML
    private Button milkStorage;
    @FXML
    private Button productFeedbackBtn;
    @FXML
    private BorderPane viewBorderPane;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void logout(ActionEvent event)throws IOException {
        Parent loginSceneParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene nextScene = new Scene(loginSceneParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextScene);
        window.show();
    }

    @FXML
    private void trackProductionRate(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "ProductionRateStage");
    }

    @FXML
    private void trackProductDetails(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "ProductDetailsStage");
    }

    @FXML
    private void viewProductOrder(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "ProductOrderStage");
    }

    @FXML
    private void viewMilkStorage(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "MilkStorageInfoStage");
    }

    @FXML
    private void viewProductFeedback(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "ViewProductFeedback");
    }
    
}
