package milkvita;

import javafx.beans.property.SimpleStringProperty;

public class FeedbackInfo {

    private SimpleStringProperty date, from, subject, feedbackMsg;

    public FeedbackInfo(String date, String from, String subject, String feedbackMsg) {
        this.date = new SimpleStringProperty(date);
        this.from = new SimpleStringProperty(from );
        this.subject = new SimpleStringProperty(subject);
        this.feedbackMsg = new SimpleStringProperty(feedbackMsg);
    }

    public void setDate(String date) {
        this.date = new SimpleStringProperty(date);
    }

    public void setFrom(String from) {
        this.from = new SimpleStringProperty(from);
    }

    public void setSubject(String subject) {
        this.subject = new SimpleStringProperty(subject);
    }

    public void setFeedbackMsg(String feedbackMsg) {
        this.feedbackMsg = new SimpleStringProperty(feedbackMsg);
    }

    public String getDate() {
        return date.get();
    }

    public String getFrom() {
        return from.get();
    }

    public String getSubject() {
        return subject.get();
    }

    public String getFeedbackMsg() {
        return feedbackMsg.get();
    }

}
