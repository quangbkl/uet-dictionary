package app.actions;

import app.dictionary.DictionaryManagement;
import app.dictionary.Word;

import java.util.ArrayList;

public class DictionaryAction extends DictionaryManagement {
    public void showSearcher(String spelling) {
        ArrayList<Word> words = this.dictionarySearcher(spelling);
        for (Word word : words) {
            System.out.println(word.getSpelling());
        }
    }

    public ArrayList<String> getStringSearchs(String spelling) {
        ArrayList<Word> words = this.dictionarySearcher(spelling);
        ArrayList<String> result = new ArrayList<>();

        for (Word word : words) {
            result.add(word.getSpelling());
        }

        return result;
    }
}
