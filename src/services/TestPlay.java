package services;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.jlp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestPlay {
    public static void main(String[] args) {
        String url = "https://translate.google.com/translate_tts?ie=UTF-8&q=mother%20fucker&tl=en&total=1&idx=0&textlen=13&tk=723264.888737&client=t&prev=input&ttsspeed=0.24";

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                InputStream audioSrc = con.getInputStream();
                new Player(new BufferedInputStream(audioSrc)).play();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
