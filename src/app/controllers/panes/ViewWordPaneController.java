package app.controllers.panes;

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
    private boolean isBookmark = false;

    @FXML
    public void onMouseClickSpeak(MouseEvent event) {
        System.out.println("Speak text");
    }

    @FXML
    public void handleRemoveWord(MouseEvent event) {
        this.state.getDictionaryAction().removeWord(view_word_spelling.getText());
        this.state.reload();
    }

    @FXML
    public void handleClickBookmark(MouseEvent event) {
        if (isBookmark) {
            trueBookmark.setVisible(true);
        } else {
            trueBookmark.setVisible(false);
        }

        isBookmark = !isBookmark;
    }

    public void initData(ContainerController state, String spelling, String explain) {
        this.state = state;
        view_word_spelling.setText(spelling);
        viewWordWebEngine = view_word_web_view.getEngine();
        viewWordWebEngine.loadContent(explain, "text/html");
    }
}
