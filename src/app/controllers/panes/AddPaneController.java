package app.controllers.panes;

import app.actions.DictionaryAction;
import app.controllers.elements.AlertController;
import app.dictionary.Word;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPaneController implements Initializable {
    @FXML
    private TextField spelling_input, explain_input;
    @FXML
    private VBox v_box_message;
    @FXML
    private Button btn_save;
    private DictionaryAction dictionaryAction = new DictionaryAction();

    private void handleChangeSpelling() {
        String spellingValue = spelling_input.getText().trim();
        Word word = dictionaryAction.dictionaryLookup(spellingValue);

        if (word != null && word.getSpelling().equals(spellingValue)) {
            this.createAlert("Word existed!", "This word already exists, you can only edit word.");
        } else {
            this.createAlert("Word doesn't exist!", "You can add a new word.");
        }

        if (spellingValue.isEmpty()) {
            this.removeAlert();
        }
    }

    private void handleChangeExplain() {
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
            this.createAlert("Quang", "BKL");
        }
    }

    private void createAlert(String header, String content) {
        this.createAlert(header, content, "new_alert_message");
    }

    private void createAlert(String header, String content, String keyId) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../../../graphical/elements/alert.fxml"));
        VBox viewWordVBox;
        try {
            viewWordVBox = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Error load alert pane.");
            return;
        }
        AlertController controller = fxmlLoader.getController();
        controller.initData(header, content);
        this.removeAlert(keyId);
        v_box_message.getChildren().add(0, viewWordVBox);

//        Timeline closeAlertTimeout = new Timeline(new KeyFrame(Duration.seconds(3), event -> v_box_message.getChildren().remove(alertVBox)));
//        closeAlertTimeout.play();
    }

    private void removeAlert() {
        this.removeAlert("new_alert_message");
    }

    private void removeAlert(String keyId) {
        ObservableList childrenVBoxMessage = v_box_message.getChildren();
        for (int i = 0; i < childrenVBoxMessage.size(); i++) {
            if (v_box_message.getChildren().get(i).getId().equals(keyId)) {
                v_box_message.getChildren().remove(i--);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
