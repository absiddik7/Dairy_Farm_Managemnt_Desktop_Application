
package milkvita;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FinanceReportDataList {
    
    public static ArrayList<FinanceReportData> financedataArray() {

        ArrayList<FinanceReportData> reportDataArray = new ArrayList<>();

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("FinaneReport.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            FinanceReportData financeReportData;
            try {

                while (true) {

                    financeReportData = (FinanceReportData) ois.readObject();
                    reportDataArray.add(financeReportData);
                }
            } catch (IOException | ClassNotFoundException e) {

            }

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return reportDataArray;
    }
    
}
