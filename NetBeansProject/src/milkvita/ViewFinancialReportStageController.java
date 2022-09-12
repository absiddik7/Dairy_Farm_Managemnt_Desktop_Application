package milkvita;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class ViewFinancialReportStageController implements Initializable {

    @FXML
    private PieChart financeChart;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button loadChartBtn;
    @FXML
    private Label LoadchartInfoLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadchartInfoLabel.setText("Pick a Date to show Finance Report!");

    }

    @FXML
    private void loadChart(ActionEvent event) {
        String date = datePicker.getValue().toString();
        ObservableList<Data> list = FXCollections.observableArrayList();

        for (FinanceReportData fd : FinanceReportDataList.financedataArray()) {
            if (fd.getDate().equals(date)) {
                LoadchartInfoLabel.setVisible(false);

                list.add(new PieChart.Data("Income", fd.getIncome()));
                list.add(new PieChart.Data("Expenses", fd.getExpenses()));
                list.add(new PieChart.Data("Profit", fd.getProfit()));

            } else {
                LoadchartInfoLabel.setText("Report not available on this date!");
            }

        }

        financeChart.setData(list);

    }

}
