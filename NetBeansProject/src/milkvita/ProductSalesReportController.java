
package milkvita;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;


public class ProductSalesReportController implements Initializable {

    @FXML
    private BarChart<?, ?> salesChart;
    @FXML
    private NumberAxis yAxix;
    @FXML
    private CategoryAxis xAxis;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
