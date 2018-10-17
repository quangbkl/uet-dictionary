package app.actions;

import app.dictionary.advanced.DictionaryHTMLManagement;
import app.dictionary.base.Word;
import app.helpers.IOHTML;
import app.helpers.SortWord;

import java.util.ArrayList;
import java.util.Collections;

public class BookmarkAction extends DictionaryAction {

    public void insertFromFile() {
        System.out.println("Start insert bookmark from file.");
        IOHTML rd = new IOHTML();
        ArrayList<Word> adds = rd.read("src/data/bookmark/E_V.txt");
        Collections.sort(adds, new SortWord());
        dictionary.setWords(adds);
        System.out.println("End insert bookmark from file.");
    }

    public void saveWordsToFile() {
        IOHTML iohtml = new IOHTML();
        iohtml.write(dictionary.getWords(), "src/data/bookmark/E_V.txt");
    }

    public ArrayList<String> getStringWords() {
        ArrayList<Word> words = this.getWords();
        ArrayList<String> result = new ArrayList<>();

        for (Word word : words) {
            result.add(word.getSpelling());
        }

        return result;
    }
}
