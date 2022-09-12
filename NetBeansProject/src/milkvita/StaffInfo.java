package milkvita;

import javafx.beans.property.SimpleStringProperty;

public class StaffInfo {

    private SimpleStringProperty id, name, designation, joinDate, password;

    public StaffInfo(String id, String name, String designation, String joinDate, String password) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.designation = new SimpleStringProperty(designation);
        this.joinDate = new SimpleStringProperty(joinDate);
        this.password = new SimpleStringProperty(password);
    }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setDesignation(String designation) {
        this.designation = new SimpleStringProperty(designation);
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = new SimpleStringProperty(joinDate);
    }

    public void setPassword(String password) {
        this.password = new SimpleStringProperty(password);
    }

    
    
    public String getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getDesignation() {
        return designation.get();
    }

    public String getJoinDate() {
        return joinDate.get();
    }

    public String getPassword() {
        return password.get();
    }

}
