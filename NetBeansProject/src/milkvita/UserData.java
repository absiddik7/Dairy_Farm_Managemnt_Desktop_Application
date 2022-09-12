package milkvita;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserData implements Serializable {

    private int id;
    private String joinDate;
    private String name;
    private String designation;
    private String password;

    private static ArrayList<UserData> userDataArray = new ArrayList<UserData>();

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserData(int id, String joinDate, String name, String designation, String password) {
        this.id = id;
        this.joinDate = joinDate;
        this.name = name;
        this.designation = designation;
        this.password = password;
    }

    public UserData() {
    }

    public int getID() {

        return id;
    }

    public String name() {

        return name;
    }

    public String designation() {

        return designation;
    }

    public String password() {

        return password;
    }

    public static String login(int id, String designation, String password) {

        for (UserData u : UserList.userArray()) {
            if (u.getID() == id && u.getDesignation().equals(designation) && u.getPassword().equals(password)) {
                return designation;
            } 
        }

        return null;
    }

    public static void setDataToDB(int id, String joinDate, String name, String designation, String password) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            f = new File("User.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

            UserData u = new UserData(
                    id, joinDate, name, designation, password
            );
            oos.writeObject(u);

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void setDefaultDataToDB(int id, String joinDate, String name, String designation, String password) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            f = new File("User.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            if (f.length() == 4) {
                UserData u = new UserData(
                        id, joinDate, name, designation, password
                );
                oos.writeObject(u);
            }

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
