package app.helpers;

import app.dictionary.base.Word;

import java.util.Comparator;

public class SortWord implements Comparator<Word> {
    @Override
    public int compare(Word o1, Word o2) {
        return o1.getSpelling().compareTo(o2.getSpelling());
    }
}