package app.dictionary;

import java.util.ArrayList;

public class DictionaryManagement {
    private Dictionary dictionary = new Dictionary();

    public void insertFromCommandline() {
        Word word = new Word();
        word.scanWord();
        dictionary.push(word);
    }

    public void insertFromFile() {}

    public ArrayList<Word> getWords() {
        return dictionary.getWords();
    }
}
