package milkvita;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;

public class GMFeedbackStageController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField feedbackSubjectTxt;
    @FXML
    private Button sendFeedbackBtn;
    @FXML
    private TableView<FeedbackInfo> feedbackDataTable;
    @FXML
    private TableColumn<FeedbackInfo, String> dateColumn;
    @FXML
    private TableColumn<FeedbackInfo, String> fromColumn;
    @FXML
    private TableColumn<FeedbackInfo, String> subjectColumn;
    @FXML
    private TableColumn<FeedbackInfo, String> feedbackColumn;
    @FXML
    private TextArea feedbackMessageTxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        feedbackColumn.setCellValueFactory(new PropertyValueFactory<>("feedbackMsg"));

        for (FeedbackData fd : FeedbackList.feedbackArray()) {
            ObservableList<FeedbackInfo> feedbackInfo = feedbackDataTable.getItems();
            feedbackInfo.add(new FeedbackInfo(fd.getDate(), fd.getFrom(), fd.getSubject(), fd.getFeedback()));
        }

    }

    @FXML
    private void sendFeedback(ActionEvent event) {
        String date = datePicker.getValue().toString();
        String from = "General Manager";
        String subject = feedbackSubjectTxt.getText();
        String feedbackMsg = feedbackMessageTxt.getText();

        FeedbackData.sendFeedback(date, from, subject, feedbackMsg);

        ObservableList<FeedbackInfo> feedbackInfo = feedbackDataTable.getItems();
        feedbackInfo.add(new FeedbackInfo(date, from, subject, feedbackMsg));

        datePicker.setValue(null);
        feedbackSubjectTxt.clear();
        feedbackMessageTxt.clear();

    }

    @FXML
    private void datePickerClear(ContextMenuEvent event) {
    }

    @FXML
    private void subjectTextFieldClear(ContextMenuEvent event) {
    }

    @FXML
    private void feedbackMsgTextFieldClear(ContextMenuEvent event) {
    }

}
