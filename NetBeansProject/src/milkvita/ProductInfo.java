
package milkvita;

import javafx.beans.property.SimpleStringProperty;


public class ProductInfo {
    
    private SimpleStringProperty date,clientName,productName,quantity,price;
   

    public ProductInfo(String date, String clientName, String productName, String quantity, String price) {
        this.date = new SimpleStringProperty(date);
        this.clientName = new SimpleStringProperty(clientName);
        this.productName = new SimpleStringProperty(productName);
        this.quantity =new SimpleStringProperty(quantity);
        this.price = new SimpleStringProperty(price);
    }

    public ProductInfo() {
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date = new SimpleStringProperty(date);
    }

    public String getClientName() {
        return clientName.get();
    }

    public void setClientName(String clientName) {
        this.clientName = new SimpleStringProperty(clientName );
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String productName) {
        this.productName = new SimpleStringProperty(productName );
    }

    public String getQuantity() {
        return quantity.get();
    }

    public void setQuantity(String quantity) {
        this.quantity = new SimpleStringProperty(quantity);
    }

    public String getPrice() {
        return price.get();
    }

    public void setPrice(String price) {
        this.price = new SimpleStringProperty(price);
    }
    
    
    
}
