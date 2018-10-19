package services;

import java.io.IOException;

public class TestDrive {
    public static void main(String[] args) {
        try {
            System.out.println(GoogleAPI.search("en", "economy"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
