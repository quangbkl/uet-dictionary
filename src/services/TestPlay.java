package services;

import java.io.*;

public class TestPlay {
    public static void main(String[] args) {
        try {
            GoogleAPI.speak("Mother fucker");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}