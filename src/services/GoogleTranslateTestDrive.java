package services;

import java.io.IOException;

public class GoogleTranslateTestDrive {
    public static void main(String[] args) {
        try {
            String translateText = GoogleTranslate.translate("vi", "economy");
            System.out.println(translateText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
