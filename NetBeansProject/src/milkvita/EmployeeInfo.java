package milkvita;

import javafx.beans.property.SimpleStringProperty;

public class EmployeeInfo {

    private SimpleStringProperty id, salary, joinDate, name, designation, password;

    public EmployeeInfo(String id, String salary, String joinDate, String name, String designation, String password) {
        this.id = new SimpleStringProperty(id);
        this.salary = new SimpleStringProperty(salary);
        this.joinDate = new SimpleStringProperty(joinDate);
        this.name = new SimpleStringProperty(name);
        this.designation = new SimpleStringProperty(designation);
        this.password = new SimpleStringProperty(password);
    }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public void setSalary(String salary) {
        this.salary = new SimpleStringProperty(salary);
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = new SimpleStringProperty(joinDate);
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setDesignation(String designation) {
        this.designation = new SimpleStringProperty(designation);
    }

    public void setPassword(String password) {
        this.password = new SimpleStringProperty(password);
    }

    public String getId() {
        return id.get();
    }

    public String getSalary() {
        return salary.get();
    }

    public String getJoinDate() {
        return joinDate.get();
    }

    public String getName() {
        return name.get();
    }

    public String getDesignation() {
        return designation.get();
    }

    public String getPassword() {
        return password.get();
    }

}
