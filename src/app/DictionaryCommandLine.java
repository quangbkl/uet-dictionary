package app;

import app.dictionary.DictionaryManagement;
import app.dictionary.Word;

import java.util.ArrayList;

public class DictionaryCommandLine {
    DictionaryManagement dm = new DictionaryManagement();

    public void showAllWords() {
        ArrayList<Word> words = dm.getWords();
        System.out.println("No\t|English\t|Tiếng Việt");
        for (Word word : words) {
            System.out.println("1\t|" + word.getSpelling() + "\t|" + word.getExplain());
        }
    }

    public void dictionaryBasic() {
        dm.insertFromCommandline();
        dm.insertFromCommandline();
        dm.insertFromCommandline();
        showAllWords();
    }
}
