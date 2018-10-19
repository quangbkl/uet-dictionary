package app.controllers.panes;

import app.dictionary.base.Word;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import services.GoogleAPI;

import java.io.IOException;
import java.util.ArrayList;

public class SearchPaneController extends ViewController {
    public void addHistory(Word word) {
        this.state.getHistoryAction().addWord(word);
    }

    public void actionSelect(String spelling) {
        ArrayList<String> stringWords = this.state.getDictionaryAction().getStringSearchs(spelling);
        search_list_view.getItems().setAll(stringWords);

        Word word = state.getDictionaryAction().dictionaryLookup(spelling);
        if (word != null) {
            controllerViewWord.initData(this.state, word.getSpelling(), word.getExplain());
            addHistory(word);
        }
    }

    @Override
    @FXML
    public void handleSelectItemListView(MouseEvent event) {
        String spelling = search_list_view.getSelectionModel().getSelectedItem();
        if (spelling == null) return;
        input_search.setText(spelling);
        actionSelect(spelling);
    }

    @Override
    public void actionSearch(String spelling) {
//        ArrayList<String> stringWords = this.state.getDictionaryAction().getStringSearchs(spelling);
//        search_list_view.getItems().setAll(stringWords);
//
//        Word word = state.getDictionaryAction().dictionaryLookup(spelling);
//        if (word != null)
//            controllerViewWord.initData(this.state, word.getSpelling(), word.getExplain());
        try {
            String explain = GoogleAPI.translate("en", "vi", spelling);
            controllerViewWord.initData(this.state, spelling, explain);
        } catch (IOException e) {
            super.actionSearch(spelling);
        }
    }
}
