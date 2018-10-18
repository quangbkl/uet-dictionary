package services;

import jdk.nashorn.internal.parser.JSONParser;

import java.io.IOException;

public class TestDrive {
    public static void main(String[] args) {
        String searchAPI = null;

//        try {
//            System.out.println(GoogleAPI.translate("economy"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            System.out.println(GoogleAPI.search("economy"));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(searchAPI);
    }
}
