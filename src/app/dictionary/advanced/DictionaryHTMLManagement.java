package app.dictionary.advanced;

import app.dictionary.base.DictionaryManagement;
import app.dictionary.base.Word;
import app.helpers.IODictionaries;
import app.helpers.IOHTML;
import app.helpers.SortWord;

import java.util.ArrayList;
import java.util.Collections;

public class DictionaryHTMLManagement extends DictionaryManagement {

    @Override
    public void insertFromFile() {
        System.out.println("Start insert from file...");
        IOHTML rd = new IOHTML();
        ArrayList<Word> adds = rd.read();
        Collections.sort(adds, new SortWord());
        dictionary.setWords(rd.read());
        System.out.println("End insert from file...");
    }

    @Override
    public void saveWordsToFile() {
        IOHTML iohtml = new IOHTML();
        iohtml.write(dictionary.getWords());
    }

    @Override
    public void addWord(Word word) {
        String explain = word.getExplain();
        if (!explain.startsWith("<html>") || !explain.contains("</html>")) {
            word.setExplain("<html>" + explain + "</html>");
        }

        dictionary.push(word);
        this.saveWordsToFile();
    }

    public void removeWord(Word word) {
        if (word != null) {
            dictionary.getWords().remove(word);
            this.saveWordsToFile();
        }
    }

    public void removeWord(String spelling) {
        Word word = dictionary.lookup(spelling);
        if (word != null) {
            this.removeWord(word);
        }
    }
}
