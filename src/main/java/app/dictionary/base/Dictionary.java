package app.dictionary.base;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> words = new ArrayList<>();

    public void push(Word word) {
        int length = words.size();
        int index = searchIndexInsert(0, length - 1, word.getSpelling());
        if (index <= length && index >= 0) words.add(index, word);
    }

    private int searchIndexInsert(int start, int end, String spelling) {
        if (end < start) return start;
        int mid = start + (end - start) / 2;
        if (mid == words.size()) return mid;
        Word word = words.get(mid);
        int compare = word.getSpelling().compareTo(spelling);
        if (compare == 0) return -1;
        if (compare > 0) return searchIndexInsert(start, mid - 1, spelling);
        return searchIndexInsert(mid + 1, end, spelling);
    }

    private Word binaryLookup(int start, int end, String spelling) {
        if (end < start) return null;
        int mid = start + (end - start) / 2;
        Word word = words.get(mid);
        String currentSpelling = word.getSpelling();
        int compare = currentSpelling.compareTo(spelling);
        if (compare == 0) return word;
        if (compare > 0) return binaryLookup(start, mid - 1, spelling);
        return binaryLookup(mid + 1, end, spelling);
    }

    private int binarySearcher(int start, int end, String spelling) {
        if (end < start) return -1;
        int mid = start + (end - start) / 2;
        Word word = words.get(mid);
        String currentSpelling = word.getSpelling();
        if (currentSpelling.startsWith(spelling)) {
            return mid;
        }
        int compare = currentSpelling.compareTo(spelling);
        if (compare == 0) return mid;
        if (compare > 0) return binarySearcher(start, mid - 1, spelling);
        return binarySearcher(mid + 1, end, spelling);
    }

    public Word lookup(String spelling) {
        return binaryLookup(0, words.size() - 1, spelling);
    }

    public ArrayList<Word> searcher(String spelling) {
        ArrayList<Word> result = new ArrayList<>();
        int index =  binarySearcher(0, words.size() - 1, spelling);
//        int index = -1;
        if (index >= 0) {
            result.add(words.get(index));
            int left = index - 1, right = index + 1;

            while (left >= 0) {
                Word leftWord = words.get(left--);
                if (leftWord.getSpelling().startsWith(spelling))
                    result.add(leftWord);
                else
                    break;
            }

            int length = words.size();
            while (right < length) {
                Word leftWord = words.get(right++);
                if (leftWord.getSpelling().startsWith(spelling))
                    result.add(leftWord);
                else
                    break;
            }
        }
        return result;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) { this.words = words; }
}
