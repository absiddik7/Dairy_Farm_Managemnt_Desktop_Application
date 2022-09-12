package milkvita;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private TextField userIdTextField;
    @FXML
    private ComboBox jobComboBox;
    @FXML
    private PasswordField userPasswordTextField;
    @FXML
    private Button loginButton;

    private static ObservableList<String> comboDesignation = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (UserData u : UserList.userArray()) {
            if (comboDesignation.contains("Employee") && (u.getDesignation().equals("Employee"))) {
            } else {
                comboDesignation.add(u.getDesignation());
            }
        }

        for (String d : comboDesignation) {
            jobComboBox.getItems().add(d);
        }

    }

    @FXML
    private void loginButtonOnClick(ActionEvent event) throws IOException {
        int id = Integer.parseInt(userIdTextField.getText());
        String designation = jobComboBox.getValue().toString();
        String password = userPasswordTextField.getText();
        
        comboDesignation.removeAll(comboDesignation);
        userIdTextField.clear();
        userPasswordTextField.clear();

        String jobTitle = UserData.login(id, designation, password);

        if (jobTitle != null) {
            String fxmlFile = jobTitle.replaceAll("\\s", "");

            Parent nextSceneParent = FXMLLoader.load(getClass().getResource(fxmlFile + "Dashboard.fxml"));
            Scene nextScene = new Scene(nextSceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(nextScene);
            window.show();

        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error Alert");
            a.setHeaderText("Login Failed!");
            a.setContentText("Oops! User Doesn't Found!");
            a.showAndWait();
        }

    }

    @FXML
    private void idTextFieldClear(ContextMenuEvent event) {

    }

    @FXML
    private void passwordTextFieldClear(ContextMenuEvent event) {

    }

}
