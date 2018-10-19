package services;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GoogleAPI {

    public static String GOOGLE_TRANSLATE_URL = "https://translate.googleapis.com/translate_a/single";
    public static String GOOGLE_AUDIO_URL = "http://translate.google.com/translate_tts";
    public static String GOOGLE_SEARCH_URL = "https://clients1.google.com/complete/search";

    public static String translate(String text) throws IOException {
        return translate("vi", text);
    }

    public static String translate(String targetLanguage, String text) throws IOException {
        return translate("auto", targetLanguage, text);
    }

    public static String translate(String sourceLanguage, String targetLanguage, String text) throws IOException {
        String url = generateTranslateURL(sourceLanguage, targetLanguage, text);
        String result = SendRequest.sendGET(url);
        return result;
    }

    public static String search(String text) throws IOException {
        return search("vi", text);
    }

    public static String search(String sourceLanguage, String text) throws IOException {
        String url = generateSearchURL(sourceLanguage, text);
        String result = SendRequest.sendGET(url);

        result = result.replace("window.google.ac.h(", "");
        result = result.substring(0, result.length() - 1);

        return result;
    }

    public static void speak(String text) throws IOException {
        speak("en", text);
    }

    public static void speak(String language, String text) throws IOException {
        String url = generateSpeakURL(language, text);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream audioSrc = con.getInputStream();
            try {
                new Player(new BufferedInputStream(audioSrc)).play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }

    private static String generateTranslateURL(String sourceLanguage, String targetLanguage, String text) throws UnsupportedEncodingException {
        String url = GOOGLE_TRANSLATE_URL + "?client=t" +
                "&sl=" + sourceLanguage +
                "&tl=" + targetLanguage +
                "&hl=" + targetLanguage + // The language of the UI
                "&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&ie=UTF-8&oe=UTF-8&otf=1&ssel=0&tsel=0&kc=1" +
                "&tk=" + generateToken(text) +
                "&q=" + URLEncoder.encode(text, "UTF-8");
        return url;
    }

    private static String generateSearchURL(String sourceLanguage, String text) throws UnsupportedEncodingException {
        String url = GOOGLE_SEARCH_URL + "?" +
                "q=" + URLEncoder.encode(text, "UTF-8") +
                "&client=translate-web&ds=translate" +
                "&hl=" + sourceLanguage;
        return url;
    }

    private static String generateSpeakURL(String language, String text) throws UnsupportedEncodingException {
        String url = GOOGLE_AUDIO_URL + "?ie=UTF-8" +
                "&q=" + URLEncoder.encode(text, "UTF-8") +
                "&tl=" + language +
                "&tk=" + generateToken(text) +
                "&client=t";
        return url;
    }

    private static int[] TKK() {
        return new int[]{0x6337E, 0x217A58DC + 0x5AF91132};
    }

    private static int shr32(int x, int bits) {
        if (x < 0) {
            long x_l = 0xffffffffl + x + 1;
            return (int) (x_l >> bits);
        }
        return x >> bits;
    }

    private static int RL(int a, String b) {//I am not entirely sure what this magic does.
        for (int c = 0; c < b.length() - 2; c += 3) {
            int d = b.charAt(c + 2);
            d = d >= 65 ? d - 87 : d - 48;
            d = b.charAt(c + 1) == '+' ? shr32(a, d) : (a << d);
            a = b.charAt(c) == '+' ? (a + (d & 0xFFFFFFFF)) : a ^ d;
        }
        return a;
    }

    private static String generateToken(String text) {
        int tkk[] = TKK();
        int b = tkk[0];
        int e = 0;
        int f = 0;
        List<Integer> d = new ArrayList<Integer>();
        for (; f < text.length(); f++) {
            int g = text.charAt(f);
            if (0x80 > g) {
                d.add(e++, g);
            } else {
                if (0x800 > g) {
                    d.add(e++, g >> 6 | 0xC0);
                } else {
                    if (0xD800 == (g & 0xFC00) && f + 1 < text.length() && 0xDC00 == (text.charAt(f + 1) & 0xFC00)) {
                        g = 0x10000 + ((g & 0x3FF) << 10) + (text.charAt(++f) & 0x3FF);
                        d.add(e++, g >> 18 | 0xF0);
                        d.add(e++, g >> 12 & 0x3F | 0x80);
                    } else {
                        d.add(e++, g >> 12 | 0xE0);
                        d.add(e++, g >> 6 & 0x3F | 0x80);
                    }
                }
                d.add(e++, g & 63 | 128);
            }
        }

        int a_i = b;
        for (e = 0; e < d.size(); e++) {
            a_i += d.get(e);
            a_i = RL(a_i, "+-a^+6");
        }
        a_i = RL(a_i, "+-3^+b+-f");
        a_i ^= tkk[1];
        long a_l;
        if (0 > a_i) {
            a_l = 0x80000000l + (a_i & 0x7FFFFFFF);
        } else {
            a_l = a_i;
        }
        a_l %= Math.pow(10, 6);
        return String.format(Locale.US, "%d.%d", a_l, a_l ^ b);
    }
}
