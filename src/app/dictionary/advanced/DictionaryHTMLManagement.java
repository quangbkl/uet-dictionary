package app.dictionary.advanced;

import app.dictionary.base.DictionaryManagement;
import app.dictionary.base.Word;
import app.helpers.IODictionaries;
import app.helpers.IOHTML;
import app.helpers.SortWord;

import java.util.ArrayList;
import java.util.Collections;

public class DictionaryHTMLManagement extends DictionaryManagement {
    public DictionaryHTMLManagement() {
        this.insertFromFile();
    }

    @Override
    public void insertFromFile() {
        System.out.println("Start insert from file.");
        IOHTML rd = new IOHTML();
        ArrayList<Word> adds = rd.read();
        Collections.sort(adds, new SortWord());
        dictionary.setWords(rd.read());
        System.out.println("End insert from file.");
    }
}
