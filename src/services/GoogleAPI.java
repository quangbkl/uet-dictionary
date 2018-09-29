package services;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleAPI {
    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "https://translate.google.com/translate_tts?ie=UTF-8&tl=en&client=tw-ob&q=pronounce";

    public static String getPronounce() {
        return "https://translate.google.com/translate_tts?ie=UTF-8&tl=en&client=tw-ob&q=pronounce";
    }

    public static void sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            System.out.println(in.toString());
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            FileOutputStream fileOutputStream new FileOutputStream("src/data/musics/test.mp3")) {
//                byte dataBuffer[] = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
//                    fileOutputStream.write(dataBuffer, 0, bytesRead);
//                }

            in.close();

//            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }
}
