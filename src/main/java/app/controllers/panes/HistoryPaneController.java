package app.controllers.panes;

import app.dictionary.base.Word;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class HistoryPaneController extends ViewController {
    @Override
    public void actionSearch(String spelling) {
        ArrayList<String> stringWords = this.state.getHistoryAction().getStringSearchs(spelling);
        search_list_view.getItems().setAll(stringWords);

        Word word = state.getHistoryAction().dictionaryLookup(spelling);
        if (word != null)
            controllerViewWord.initData(this.state, word.getSpelling(), word.getExplain());
    }

    @Override
    @FXML
    public void handleChangeInputSearch(KeyEvent event) {
        if (event.getSource() == input_search) {
            String searchText = input_search.getText();
            if (!searchText.isEmpty()) {
                actionSearch(searchText);
            } else {
                ArrayList<String> stringWords = this.state.getHistoryAction().getStringWords();
                search_list_view.getItems().setAll(stringWords);
            }
        }
    }

    public void reset() {
        input_search.setText("");
        ArrayList<String> stringWords = this.state.getHistoryAction().getStringWords();
        search_list_view.getItems().setAll(stringWords);
        controllerViewWord.initData(this.state, "", "");
    }

    public void reload() {
        if (state == null) return;

        String searchText = input_search.getText();
        if (!searchText.isEmpty()) {
            actionSearch(searchText);
        } else {
            ArrayList<String> stringWords = this.state.getHistoryAction().getStringWords();
            search_list_view.getItems().setAll(stringWords);
        }

        controllerViewWord.reload();
    }
}