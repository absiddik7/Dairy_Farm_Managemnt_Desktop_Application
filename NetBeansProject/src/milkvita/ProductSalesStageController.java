package milkvita;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;

public class ProductSalesStageController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField clientName;
    @FXML
    private TextField quantity;
    @FXML
    private Button updateBtn;
    @FXML
    private TextField price;
    @FXML
    private ComboBox productNameComboBox;
    @FXML
    private TableColumn<ProductInfo, String> dateColumn;
    @FXML
    private TableColumn<ProductInfo, String> clientNameColumn;
    @FXML
    private TableColumn<ProductInfo, String> productNameColumn;
    @FXML
    private TableColumn<ProductInfo, String> quantityColumn;
    @FXML
    private TableColumn<ProductInfo, String> priceColumn;
    @FXML
    private TableView<ProductInfo> salesTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productNameComboBox.getItems().addAll("Liquid Milk", "Butter", "Ghee", "Ice Cream", "Milk Powder");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        for (ProductData pd : ProductList.productArray()) {
            ObservableList<ProductInfo> productData = salesTable.getItems();
            productData.add(new ProductInfo(pd.getDate(), pd.getClientName(), pd.getProductName(), Integer.toString(pd.getQuantity()), Integer.toString(pd.getPrice())));
        }
    }

    @FXML
    private void updateSales(ActionEvent event) {
        String date = datePicker.getValue().toString();
        String clientname = clientName.getText();
        String prodctName = productNameComboBox.getValue().toString();
        int quantitys = Integer.parseInt(quantity.getText());
        int netPrice = Integer.parseInt(price.getText());

        ProductData.setDataToDB(date, clientname, prodctName, quantitys, netPrice);

        ObservableList<ProductInfo> productData = salesTable.getItems();
        productData.add(new ProductInfo(date, clientname, prodctName, Integer.toString(netPrice), Integer.toString(netPrice)));

        datePicker.setValue(null);
        clientName.clear();
        productNameComboBox.setValue(null);
        quantity.clear();
        price.clear();

    }

    @FXML
    private void dateClear(ContextMenuEvent event) {
    }

    @FXML
    private void cNameClear(ContextMenuEvent event) {
    }

    @FXML
    private void qClear(ContextMenuEvent event) {
    }

    @FXML
    private void pClear(ContextMenuEvent event) {
    }

}
