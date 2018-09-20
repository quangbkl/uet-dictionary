package app.dictionary;

import app.files.ReadDictionaries;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary dictionary = new Dictionary();

    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        String spelling = scanner.nextLine();
        String explain = scanner.nextLine();
        Word word = new Word(spelling, explain);
        dictionary.push(word);
    }

    public void insertFromFile() {
        ReadDictionaries rd = new ReadDictionaries();
        dictionary.setWords(rd.read());
    }

    public ArrayList<Word> getWords() {
        return dictionary.getWords();
    }
}
