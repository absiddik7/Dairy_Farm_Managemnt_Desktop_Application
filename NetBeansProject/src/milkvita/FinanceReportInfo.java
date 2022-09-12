
package milkvita;

import javafx.beans.property.SimpleStringProperty;


public class FinanceReportInfo {
    
    private SimpleStringProperty date, income, expenses,profit;

    public FinanceReportInfo(String date, String income, String expenses, String profit) {
        this.date = new SimpleStringProperty(date);
        this.income = new SimpleStringProperty(income);
        this.expenses = new SimpleStringProperty(expenses);
        this.profit = new SimpleStringProperty(profit);
    }

    public FinanceReportInfo() {
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date = new SimpleStringProperty(date);
    }

    public String getIncome() {
        return income.get();
    }

    public void setIncome(String income) {
        this.income = new SimpleStringProperty(income);
    }

    public String getExpenses() {
        return expenses.get();
    }

    public void setExpenses(String expenses) {
        this.expenses = new SimpleStringProperty(expenses);
    }

    public String getProfit() {
        return profit.get();
    }

    public void setProfit(String profit) {
        this.profit = new SimpleStringProperty(profit);
    }
    
    
    
    
}
