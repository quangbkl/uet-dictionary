package app.dictionary;

public class DictionaryManagement {
    private Dictionary dictionary = new Dictionary();

    public void insertFromCommandline() {
        Word word = new Word();
        word.scanWord();
        dictionary.push(word);
    }
}
