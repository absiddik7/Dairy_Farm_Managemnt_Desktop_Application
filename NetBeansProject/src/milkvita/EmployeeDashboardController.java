
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


public class EmployeeDashboardController implements Initializable {

    @FXML
    private Button logoutBtn;
    @FXML
    private Button trackProductSale;
    @FXML
    private Button trackMilkSupply;
    @FXML
    private Button trackOrder;
    @FXML
    private Button saveBuyerInfo;
    @FXML
    private Button productFeedback;
    @FXML
    private Label hintsLabel;
    @FXML
    private BorderPane viewBorderPane;

    
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
    private void trackProductSale(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "ProductSalesStage");
        hintsLabel.setVisible(false);
    }

    @FXML
    private void trackMilkSupply(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "MilkPurchaseStage");
        hintsLabel.setVisible(false);
    }

    @FXML
    private void trackOrder(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "ProductOrderStage");
        hintsLabel.setVisible(false);
    }

    @FXML
    private void saveBuyerInfo(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "BuyersInfoStage");
        hintsLabel.setVisible(false);
    }

    @FXML
    private void productFeedback(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "ProductFeedbackStage");
        hintsLabel.setVisible(false);
    }
    
}
