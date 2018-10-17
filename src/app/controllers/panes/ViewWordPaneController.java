package app.controllers.panes;

import app.dictionary.base.Word;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ViewWordPaneController {
    @FXML
    private Label view_word_spelling;
    @FXML
    private WebView view_word_web_view;
    @FXML
    private Label trueBookmark;
    private WebEngine viewWordWebEngine;
    private ContainerController state;

    @FXML
    public void onMouseClickSpeak(MouseEvent event) {
        System.out.println("Speak text");
    }

    @FXML
    public void handleRemoveWord(MouseEvent event) {
        this.state.getDictionaryAction().removeWord(view_word_spelling.getText());
        this.state.reset();
    }

    @FXML
    public void handleClickBookmark(MouseEvent event) {
        String spelling = view_word_spelling.getText();
        Word word = state.getBookmarkAction().dictionaryLookup(spelling);
        if (word != null) {
            this.removeBookmark();
//            trueBookmark.setVisible(false);
        } else {
            this.addBookmark();
//            trueBookmark.setVisible(true);
        }
        this.loadBookmark();
        this.state.reloadBookmark();
    }

    public void addBookmark() {
        String spelling = view_word_spelling.getText();
        Word word = this.state.getDictionaryAction().dictionaryLookup(spelling);
        if (word != null) this.state.getBookmarkAction().addWord(word);
    }

    public void removeBookmark() {
        String spelling = view_word_spelling.getText();
        this.state.getBookmarkAction().removeWord(spelling);
        this.state.resetBookmark();
    }

    public void loadBookmark() {
        String spelling = view_word_spelling.getText();
        Word word = state.getBookmarkAction().dictionaryLookup(spelling);
        if (word != null) {
            trueBookmark.setVisible(true);
        } else {
            trueBookmark.setVisible(false);
        }
    }

    public void reload() {
        this.loadBookmark();
    }

    public void initData(ContainerController state, String spelling, String explain) {
        this.state = state;
        view_word_spelling.setText(spelling);
        viewWordWebEngine = view_word_web_view.getEngine();
        viewWordWebEngine.loadContent(explain, "text/html");

        this.loadBookmark();
    }
}
