package services;

import jdk.nashorn.internal.parser.JSONParser;

import java.io.IOException;

public class TestDrive {
    public static void main(String[] args) {
        String searchAPI = null;

        try {
            searchAPI = SendRequest.sendGET("https://clients1.google.com/complete/search?q=economic&hl=en&ds=translate&client=translate-web");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(searchAPI);
    }
}
