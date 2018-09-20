package app.dictionary;

public class Word {
    private String spelling, explain;

    public Word(String spelling, String explain) {
        this.spelling = spelling;
        this.explain = explain;
    }

    public Word() {
        this("", "");
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getExplain() { return explain; }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
