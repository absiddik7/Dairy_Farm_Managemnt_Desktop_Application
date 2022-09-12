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

public class GMHiringEmployeeStageController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField idTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField designationTxt;
    @FXML
    private Button addBtn;
    @FXML
    private TableView<EmployeeInfo> employeeInfoTable;
    @FXML
    private TableColumn<EmployeeInfo, String> dateColumn;
    @FXML
    private TableColumn<EmployeeInfo, String> idColumn;
    @FXML
    private TableColumn<EmployeeInfo, String> nameColumn;
    @FXML
    private TableColumn<EmployeeInfo, String> designationCoulmn;
    @FXML
    private TableColumn<EmployeeInfo, String> salaryColumn;
    @FXML
    private TextField passwordTxt;
    @FXML
    private TextField salaryTxt;
    @FXML
    private TableColumn<EmployeeInfo, String> passwordCollumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        designationCoulmn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        passwordCollumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        
        for (EmployeeData ed : EmployeeList.employeeArray()) {
            ObservableList<EmployeeInfo> employeeData = employeeInfoTable.getItems();
            employeeData .add(new EmployeeInfo(Integer.toString(ed.getId()), Integer.toString(ed.getSalary()),  ed.getJoinDate(),ed.getName(),ed.getDesignation(), ed.getPassword()));
        }
       
    }

    @FXML
    private void addEmployee(ActionEvent event) {
        
        int id = Integer.parseInt(idTxt.getText());
        int salary = Integer.parseInt(salaryTxt.getText());
        String joinDate = datePicker.getValue().toString();
        String name = nameTxt.getText();
        String defaultDesignation = designationTxt.getText();
        String defaultPassword = passwordTxt.getText();

        EmployeeData.addEmployee(id, salary,joinDate, name, defaultDesignation, defaultPassword);
        UserData.setDataToDB(id, joinDate, name, defaultDesignation, defaultPassword);

        ObservableList<EmployeeInfo> employeeData = employeeInfoTable.getItems();
        employeeData.add(new EmployeeInfo(Integer.toString(id),Integer.toString(salary), joinDate, name, defaultDesignation, defaultPassword));

        datePicker.setValue(null);
        idTxt.clear();
        salaryTxt.clear();
        nameTxt.clear();
        designationTxt.clear();
        passwordTxt.clear();
        
        
    }

    @FXML
    private void datePickerClear(ContextMenuEvent event) {
    }

    @FXML
    private void idTxtClear(ContextMenuEvent event) {
    }

    @FXML
    private void nameTxtClear(ContextMenuEvent event) {
    }

    @FXML
    private void degTxtClear(ContextMenuEvent event) {
    }

    @FXML
    private void salaryTxtCear(ContextMenuEvent event) {
    }

    @FXML
    private void passTxtClear(ContextMenuEvent event) {
    }
    
    

}
