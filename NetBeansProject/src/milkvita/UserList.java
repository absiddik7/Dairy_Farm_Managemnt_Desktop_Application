package milkvita;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserList {

    public static ArrayList<UserData> userArray() {

        ArrayList<UserData> userDataArray = new ArrayList<>();

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("User.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            UserData userData;
            try {

                while (true) {

                    userData = (UserData) ois.readObject();
                    userDataArray.add(userData);
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

        return userDataArray;
    }
}
