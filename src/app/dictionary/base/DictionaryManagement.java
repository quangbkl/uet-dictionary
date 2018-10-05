package app.dictionary.base;

import app.helpers.IODictionaries;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    protected Dictionary dictionary = new Dictionary();

    public DictionaryManagement() {
        this.insertFromFile();
    }

    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        String spelling = scanner.nextLine();
        String explain = scanner.nextLine();
        Word word = new Word(spelling, explain);
        dictionary.push(word);
    }

    public void insertFromFile() {
        IODictionaries rd = new IODictionaries();
//        dictionary.setWords(rd.read());   //TODO: Sort
        ArrayList<Word> adds = rd.read();
        for (Word add : adds) {
            dictionary.push(add);
        }
    }

    public void dictionaryLookup() {
        Scanner scanner = new Scanner(System.in);
        String spelling = scanner.nextLine();
        Word word = dictionary.lookup(spelling);
        System.out.println(word.getExplain());
    }

    public Word dictionaryLookup(String text) {
        return dictionary.lookup(text);
    }

    public void dictionarySearcher() {
        Scanner scanner = new Scanner(System.in);
        String spelling = scanner.nextLine();
        ArrayList<Word> words = dictionary.searcher(spelling);

        for (Word word : words) {
            System.out.print(word.getSpelling());
            System.out.println(" " + word.getExplain());
        }
    }

    public ArrayList<Word> dictionarySearcher(String searchText) {
        if (searchText.equals("")) return new ArrayList<>();
        return dictionary.searcher(searchText);
    }

    public void dictionaryExportToFile() {
        IODictionaries iod = new IODictionaries();
        iod.write(dictionary.getWords());
    }

    public ArrayList<Word> getWords() {
        return dictionary.getWords();
    }
}
