package app.controllers.panes;

import app.dictionary.base.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class EditPaneController extends ActionController {
    @FXML
    private TextField old_word_input;

    protected boolean isExistOldWord() {
        String spellingValue = old_word_input.getText().trim();
        Word word = this.state.getDictionaryAction().dictionaryLookup(spellingValue);
        return word != null && word.getSpelling().equals(spellingValue);
    }

    @Override
    @FXML
    public void handleChangeInput(KeyEvent event) {
        super.handleChangeInput(event);
        if (event.getSource() == old_word_input) {
            this.handleChangeOldWord();
        }
    }

    private void actionChangeOldWord() {
        if (!isExistOldWord()) {
            this.createAlert("Word doesn't exist!", "You can not edit word.");
        } else {
            this.removeAlert();
        }

        if (old_word_input.getText().isEmpty()) {
            this.removeAlert();
        }
    }

    private void handleChangeOldWord() {
        this.actionChangeOldWord();
    }

    @Override
    @FXML
    public void handleClickSave(ActionEvent event) {
        if (event.getSource() == btn_save) {
            if (!isExistSpelling() && isExistOldWord()) {
                Word word = new Word(spelling_input.getText().trim(), explain_input.getText().trim());
                state.getDictionaryAction().addWord(word);
                this.createAlert("Success!", "Edit word successfully.");
            } else if (isExistSpelling()) {
                this.createAlert("Fail!", "This new word already exists.");
            } else if (!isExistOldWord()) {
                this.createAlert("Fail!", "This old word not exists.");
            } else {
                this.createAlert("Fail!", "Can not edit word.");
            }
        }
    }

    public void initData(ContainerController state, String spelling) {
        super.initData(state);
        old_word_input.setText(spelling);
        actionChangeOldWord();
    }
}
