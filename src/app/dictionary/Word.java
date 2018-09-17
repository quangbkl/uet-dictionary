package app.dictionary;

import java.util.Scanner;

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

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public void scanWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter spelling...");
        this.spelling = scanner.nextLine();
        System.out.println("Enter explain...");
        this.explain = scanner.nextLine();
    }

    public void printWord() {
        System.out.println(spelling + " " + explain);
    }
}
