package milkvita;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    
    public static void main(String[] args) {

        defaultLoginInfo();
        launch(args);

    }


    private static void defaultLoginInfo() {
        int defaultID = 2233;
        String joinDate = "2021-05-08";
        String name = "Abubakkar";
        String defaultDesignation = "Chairman";
        String defaultPassword = "1234";
        
        UserData.setDefaultDataToDB(defaultID, joinDate, name, defaultDesignation, defaultPassword);
        
    }

}
