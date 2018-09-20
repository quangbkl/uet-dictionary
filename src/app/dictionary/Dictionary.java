package app.dictionary;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> words = new ArrayList<>();

    public void push(Word word) {
        int length = words.size();
        int index = searchIndexInsert(0, length - 1, word.getSpelling());
        if (index < length) words.add(index, word);
        else words.add(word);
    }

    public int searchIndexInsert(int start, int end, String spelling) {
        if (end < start) return start;
        int mid = start + (end - start) / 2;
        if (mid == words.size()) return mid;
        Word word = words.get(mid);
        int compare = word.getSpelling().compareTo(spelling);
        if (compare == 0) return -1;
        if (compare > 0) return searchIndexInsert(start, mid - 1, spelling);
        return searchIndexInsert(mid + 1, end, spelling);
    }

    private Word binarySearch(int start, int end, String spelling) {
        if (end < start) return null;
        int mid = start + (end - start) / 2;
        Word word = words.get(mid);
        int compare = word.getSpelling().compareTo(spelling);
        if (compare == 0) return word;
        if (compare > 0) return binarySearch(start, mid - 1, spelling);
        return binarySearch(mid + 1, end, spelling);
    }

    public Word search(String spelling) {
        return binarySearch(0, words.size() - 1, spelling);
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) { this.words = words; }
}
