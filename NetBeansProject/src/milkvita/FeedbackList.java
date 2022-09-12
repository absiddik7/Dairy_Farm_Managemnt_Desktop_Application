package milkvita;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackList {

    public static ArrayList<FeedbackData> feedbackArray() {
        ArrayList<FeedbackData> feedbackDataArray = new ArrayList<FeedbackData>();
        
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("Feedback.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            FeedbackData feedbackData;
            try {

                while (true) {

                    feedbackData = (FeedbackData) ois.readObject();
                    feedbackDataArray.add(feedbackData);
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
        return feedbackDataArray;
    }
}
