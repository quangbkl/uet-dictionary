package app.controllers.panes;

import app.dictionary.base.Word;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import services.GoogleAPI;

import java.io.IOException;

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
        String spelling = view_word_spelling.getText();
        try {
            GoogleAPI.speak(spelling);
        } catch (IOException e) {
            System.out.println("[ERROR]: Speak word.");
        }
    }

    @FXML
    public void handleRemoveWord(MouseEvent event) {
        this.state.getDictionaryAction().removeWord(view_word_spelling.getText());
        this.state.reset();
    }

    @FXML
    public void handleClickEdit(MouseEvent event) {
        String spelling = view_word_spelling.getText();
        this.state.showEditPane(spelling);
    }

    @FXML
    public void handleClickBookmark(MouseEvent event) {
        String spelling = view_word_spelling.getText();
        Word word = state.getBookmarkAction().dictionaryLookup(spelling);
        if (word != null) {
            this.removeBookmark();
        } else {
            this.addBookmark();
        }
        this.loadBookmark();
        this.state.reloadBookmark();
    }

    public void addBookmark() {
        String spelling = view_word_spelling.getText();
        String html = (String) viewWordWebEngine.executeScript("document.documentElement.outerHTML");
        this.state.getBookmarkAction().addWord(new Word(spelling, html));
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
        viewWordWebEngine.setUserStyleSheetLocation(getClass().getResource("../../../graphical/panes/web_view.css").toString());

        this.loadBookmark();
    }
}
