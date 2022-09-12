package milkvita;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;

public class StaffAccountStageController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField designationTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button addStaffButton;
    @FXML
    private TableView<StaffInfo> staffAccountInfoTable;

    @FXML
    private TableColumn<StaffInfo, String> idColumn;
    @FXML
    private TableColumn<StaffInfo, String> nameColumn;
    @FXML
    private TableColumn<StaffInfo, String> designationColumn;
    @FXML
    private TableColumn<StaffInfo, String> joinDateColumn;
    @FXML
    private TableColumn<StaffInfo, String> passwordColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        joinDateColumn.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        for (UserData u : UserList.userArray()) {
            ObservableList<StaffInfo> staffData = staffAccountInfoTable.getItems();
            staffData.add(new StaffInfo(Integer.toString(u.getID()), u.getName(), u.getDesignation(), u.getJoinDate(), u.getPassword()));
        }
    }

    @FXML
    private void saveStaffAccount(ActionEvent event) {

        int id = Integer.parseInt(idTextField.getText());
        String joinDate = datePicker.getValue().toString();
        String name = nameTextField.getText();
        String defaultDesignation = designationTextField.getText();
        String defaultPassword = passwordTextField.getText();

        UserData.setDataToDB(id, joinDate, name, defaultDesignation, defaultPassword);

        ObservableList<StaffInfo> staffData = staffAccountInfoTable.getItems();
        staffData.add(new StaffInfo(Integer.toString(id), name, defaultDesignation, joinDate, defaultPassword));

        datePicker.setValue(null);
        idTextField.clear();
        nameTextField.clear();
        designationTextField.clear();
        passwordTextField.clear();
    }

    @FXML
    private void datePickerClear(ContextMenuEvent event) {

    }

    @FXML
    private void idTextFieldClear(ContextMenuEvent event) {

    }

    @FXML
    private void nameTextFieldClear(ContextMenuEvent event) {

    }

    @FXML
    private void designationTextFieldClear(ContextMenuEvent event) {

    }

    @FXML
    private void passwordTextFieldClear(ContextMenuEvent event) {

    }

}
