package app;

import app.dictionary.DictionaryManagement;
import app.dictionary.Word;

import java.util.ArrayList;

public class DictionaryCommandLine {
    DictionaryManagement dm = new DictionaryManagement();

    public void showAllWords() {
        ArrayList<Word> words = dm.getWords();
        int count = 1;
        System.out.println("No\t|English\t|Tiếng Việt");
        for (Word word : words) {
            System.out.println(count++ + "\t|" + word.getSpelling() + "\t|" + word.getExplain());
        }
    }

    public void dictionaryBasic() {
        dm.insertFromCommandline();
        dm.insertFromCommandline();
        dm.insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvanced() {
        dm.insertFromFile();
        dm.dictionaryExportToFile();
//        showAllWords();
//        dm.dictionaryLookup();
        dm.dictionarySearcher();
    }
}
