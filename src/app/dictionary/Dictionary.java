package app.dictionary;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> words = new ArrayList<>();

    public void push(Word word) {
        int index = words.size() - 1;
        String insertWord = word.getSpelling();
        if (index >= 0) {
            while (index >= 0) {
                String current = words.get(index).getSpelling();
                if (insertWord.compareTo(current) > 0) break;
                index--;
            }
            words.add(index + 1, word);
        } else {
            words.add(word);
        }
    }

    public void create() {
        Word word = new Word();
        word.scanWord();
        push(word);
    }

    private Word binarySearch(int start, int end, String spelling) {
        if (start >= end) return null;
        int mid = (start + end) / 2;
        Word word = words.get(mid);
        int compare = word.getSpelling().compareTo(spelling);
        if (compare == 0) {
            return word;
        } else if (compare > 0) {
            return binarySearch(start, mid, spelling);
        } else  {
            return binarySearch(mid + 1, end, spelling);
        }
    }

    public Word search(String spelling) {
        return binarySearch(0, words.size(), spelling);
    }

    public void printWords() {
        for (Word item : words) {
            System.out.println(item.getSpelling());
        }
    }
}
