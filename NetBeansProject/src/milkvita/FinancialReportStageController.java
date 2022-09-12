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

public class FinancialReportStageController implements Initializable {

    @FXML
    private TextField incomeTxt;
    @FXML
    private TextField expensesTxt;
    @FXML
    private Button uploadBtn;
    @FXML
    private TableView<FinanceReportInfo> financeReportTable;
    @FXML
    private TableColumn<FinanceReportInfo, String> dateColumn;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField profitTxt;
    @FXML
    private TableColumn<FinanceReportInfo, String> incomeColumn;
    @FXML
    private TableColumn<FinanceReportInfo, String> expensesColumn;
    @FXML
    private TableColumn<FinanceReportInfo, String> profitColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        incomeColumn.setCellValueFactory(new PropertyValueFactory<>("income"));
        expensesColumn.setCellValueFactory(new PropertyValueFactory<>("expenses"));
        profitColumn.setCellValueFactory(new PropertyValueFactory<>("profit"));

        for (FinanceReportData fr : FinanceReportDataList.financedataArray()) {
            ObservableList<FinanceReportInfo> reportData = financeReportTable.getItems();
            reportData.add(new FinanceReportInfo(fr.getDate(),Integer.toString(fr.getIncome()),Integer.toString(fr.getExpenses()),Integer.toString(fr.getProfit())));
        }
    }

    @FXML
    private void uploadReport(ActionEvent event) {
        String date = datePicker.getValue().toString();
        int income = Integer.parseInt(incomeTxt.getText());
        int expenses = Integer.parseInt(expensesTxt.getText());
        int profit = Integer.parseInt(profitTxt.getText());

        FinanceReportData.setDataToDB(date, income, expenses, profit);

        datePicker.setValue(null);
        incomeTxt.clear();
        expensesTxt.clear();
        profitTxt.clear();

    }

    @FXML
    private void incomeTextFieldClear(ContextMenuEvent event) {
    }

    @FXML
    private void expensesTextFieldClear(ContextMenuEvent event) {
    }

    @FXML
    private void datePickerClear(ContextMenuEvent event) {
    }

    @FXML
    private void profitTextFieldClear(ContextMenuEvent event) {
    }

}
