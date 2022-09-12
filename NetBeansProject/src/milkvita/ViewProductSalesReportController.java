package milkvita;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;


public class ViewProductSalesReportController implements Initializable {

    @FXML
    private BarChart<String, Number> salesChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ArrayList<String> productName = new ArrayList<>();

        productName.add("Liquid Milk");
        productName.add("Butter");
        productName.add("Ghee");
        productName.add("Ice Cream");
        productName.add("Milk Powder");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
 
        for (ProductData pd : ProductList.productArray()) {
            
            for (int i = 0; i < productName.size(); i++) {
                
                if (pd.getProductName().equals( productName.get(i))) {
                    
                    series.getData().add(new XYChart.Data<>(productName.get(i), pd.getPrice()));
                } 
            }
        }

        series.setName("Sales Profit");
        salesChart.getData().add(series);
    }

}
