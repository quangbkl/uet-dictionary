package app.controllers.panes;

import app.controllers.elements.AlertController;
import app.dictionary.base.Word;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActionController implements Initializable {
    protected ContainerController state;
    @FXML
    protected TextField spelling_input;
    @FXML
    protected TextArea explain_input;
    @FXML
    protected VBox v_box_message;
    @FXML
    protected Button btn_save;

    protected boolean isExistSpelling() {
        String spellingValue = spelling_input.getText().trim();
        Word word = state.getDictionaryAction().dictionaryLookup(spellingValue);
        return word != null && word.getSpelling().equals(spellingValue);
    }

    protected void handleChangeSpelling() {
        String spellingValue = spelling_input.getText().trim();

        if (this.isExistSpelling()) {
            this.createAlert("Word existed!", "This word already exists, you can only edit word.");
        } else {
            this.removeAlert();
//            this.createAlert("Word doesn't exist!", "You can add a new word.");
        }

        if (spellingValue.isEmpty()) {
            this.removeAlert();
        }
    }

    protected void handleChangeExplain() {
        String explainValue = explain_input.getText();
        System.out.println("Change explain: " + explainValue);
    }

    @FXML
    public void handleChangeInput(KeyEvent event) {
        if (event.getSource() == spelling_input) {
            this.handleChangeSpelling();
        } else if (event.getSource() == explain_input) {
            this.handleChangeExplain();
        }
    }

    @FXML
    public void handleClickSave(ActionEvent event) {
        if (event.getSource() == btn_save) {
            if (!isExistSpelling()) {
                String spelling = spelling_input.getText().trim();
                Word word = new Word(spelling, explain_input.getText().trim());
                state.getDictionaryAction().removeWord(spelling);
                state.getDictionaryAction().addWord(word);
                this.createAlert("Success!", "Add new word successfully.");
            } else {
                this.createAlert("Fail!", "This word already exists, you can only edit word.");
            }
        }
    }

    protected void createAlert(String header, String content) {
        this.createAlert(header, content, "new_alert_message", true);
    }

    protected void createAlert(String header, String content, String keyId) {
        this.createAlert(header, content, keyId, true);
    }

    protected void createAlert(String header, String content, String keyId, boolean close) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../../../graphical/elements/alert.fxml"));
        final VBox alertVBox;
        try {
            alertVBox = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Error load alert pane.");
            return;
        }
        alertVBox.setId("new_alert_message");
        AlertController controller = fxmlLoader.getController();
        controller.initData(header, content);
        this.removeAlert(keyId);
        v_box_message.getChildren().add(0, alertVBox);

        if (close) {
            Timeline closeAlertTimeout = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    v_box_message.getChildren().remove(alertVBox);
                }
            }));
            closeAlertTimeout.play();
        }
    }

    protected void removeAlert() {
        this.removeAlert("new_alert_message");
    }

    protected void removeAlert(String keyId) {
        ObservableList childrenVBoxMessage = v_box_message.getChildren();
        for (int i = 0; i < childrenVBoxMessage.size(); i++) {
            String idAlert = v_box_message.getChildren().get(i).getId();
            if (idAlert != null && idAlert.equals(keyId)) {
                v_box_message.getChildren().remove(i--);
            }
        }
    }

    public void initData(ContainerController state) {
        this.state = state;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
