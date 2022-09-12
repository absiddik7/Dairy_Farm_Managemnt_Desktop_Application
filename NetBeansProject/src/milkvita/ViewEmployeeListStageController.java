
package milkvita;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ViewEmployeeListStageController implements Initializable {

 
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
    private TableColumn<EmployeeInfo, String> passwordCollumn;
    @FXML
    private TableView<EmployeeInfo> viewEmployeeInfoTable;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        designationCoulmn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        passwordCollumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        for (EmployeeData ed : EmployeeList.employeeArray()) {
            ObservableList<EmployeeInfo> employeeData = viewEmployeeInfoTable.getItems();
            employeeData .add(new EmployeeInfo(Integer.toString(ed.getId()), Integer.toString(ed.getSalary()),  ed.getJoinDate(),ed.getName(),ed.getDesignation(), ed.getPassword()));
        }
        
    }    
    
}
