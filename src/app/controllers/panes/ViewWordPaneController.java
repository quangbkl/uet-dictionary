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
    private WebEngine viewWordWebEngine;

    @FXML
    public void onMouseClickSpeak(MouseEvent event) {
        System.out.println("Speak text");
    }

    public void initData(String spelling, String explain) {
        view_word_spelling.setText(spelling);
//        view_word_explain.setText(explain);
        viewWordWebEngine = view_word_web_view.getEngine();
        viewWordWebEngine.loadContent(explain, "text/html");
    }
}
