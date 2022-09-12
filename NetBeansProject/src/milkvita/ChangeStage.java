package milkvita;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class ChangeStage {

    Parent root;

    public ChangeStage(BorderPane viewBorderPane, String fxmlFile) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlFile + ".fxml"));
            viewBorderPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
