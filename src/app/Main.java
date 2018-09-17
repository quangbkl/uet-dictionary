package app;

public class Main {
    public static void main(String[] args) {
//        Dictionary d = new Dictionary();
//        Word word1 = new Word("quang1", "bkl1");
//        Word word2 = new Word("quang5", "bkl2");
//        Word word3 = new Word("quang3", "bkl3");
//        Word word4 = new Word("quang7", "bkl4");
//        d.push(word2);
//        d.push(word4);
//        d.push(word3);
//        d.push(word1);
//        d.printWords();
//        Word searchWord = d.search("quang3");
//        if (searchWord != null) searchWord.printWord();
//        int x = d.searchIndexInsert(0, 4, "quang6");
//        System.out.println(x);
        DictionaryCommandLine dc = new DictionaryCommandLine();
        dc.dictionaryBasic();
    }
}
