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


public class FinanceManagerDashboardController implements Initializable {

    @FXML
    private Button logoutBtn;
    @FXML
    private Button incomeBtn;
    @FXML
    private Button expensesBtn;
    @FXML
    private Button salesInfoBtn;
    @FXML
    private Button milkPurchaseInfoBtn;
    @FXML
    private Button financeReportBtn;
    @FXML
    private BorderPane viewBorderPane;
    @FXML
    private Label hintsLabel;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void trackIncome(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "IncomeStage");
        hintsLabel.setVisible(false);
        hintsLabel.setVisible(false);
        
    }

    @FXML
    private void trackExpenses(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "ExpensesStage");
        hintsLabel.setVisible(false);
    }

    @FXML
    private void viewSalesInfo(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "SalesInfoStage");
        hintsLabel.setVisible(false);
    }

    @FXML
    private void viewMilkPurchaseInfo(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "MilkPurchaseInfoStage");
        hintsLabel.setVisible(false);
    }

    @FXML
    private void submitFinanceReport(ActionEvent event) throws IOException {
        ChangeStage s = new ChangeStage(viewBorderPane, "FinancialReportStage");
        hintsLabel.setVisible(false);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent loginSceneParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene nextScene = new Scene(loginSceneParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextScene);
        window.show();
    }
    
}
