package app;

import app.dictionary.Dictionary;
import app.dictionary.Word;

public class Main {
    public static void main(String[] args) {
        Dictionary d = new Dictionary();
        Word word1 = new Word("quang1", "bkl1");
        Word word2 = new Word("quang2", "bkl2");
        Word word3 = new Word("quang3", "bkl3");
        Word word4 = new Word("quang4", "bkl4");
        d.push(word2);
        d.push(word4);
        d.push(word3);
        d.push(word1);
//        d.printWords();
        Word searchWord = d.search("quang3");
        if (searchWord != null) searchWord.printWord();
    }
}
