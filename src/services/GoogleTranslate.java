/**
*Github tác giả: https://github.com/goxr3plus/
*@author
 */

package services;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public final class GoogleTranslate { //Class marked as final since all methods are static

    private static final String GOOGLE_TRANSLATE_URL = "http://translate.google.com/translate_a/single";

    private GoogleTranslate() {
    };

    public static String getDisplayLanguage(String languageCode) {
        return ( new Locale(languageCode) ).getDisplayLanguage();
    }

    private static String generateURL(String sourceLanguage , String targetLanguage , String text) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(text, "UTF-8"); //Encode
        StringBuilder sb = new StringBuilder();
        sb.append(GOOGLE_TRANSLATE_URL);
        sb.append("?client=webapp"); //The client parameter
        sb.append("&hl=en"); //The language of the UI?
        sb.append("&sl="); //Source language
        sb.append(sourceLanguage);
        sb.append("&tl="); //Target language
        sb.append(targetLanguage);
        sb.append("&q=");
        sb.append(encoded);
        sb.append("&multires=1");//Necessary but unknown parameters
        sb.append("&otf=0");
        sb.append("&pc=0");
        sb.append("&trs=1");
        sb.append("&ssel=0");
        sb.append("&tsel=0");
        sb.append("&kc=1");
        sb.append("&dt=t");//This parameters requests the translated text back.
        //Other dt parameters request additional information such as pronunciation, and so on.
        //TODO Modify API so that the user may request this additional information.
        sb.append("&ie=UTF-8"); //Input encoding
        sb.append("&oe=UTF-8"); //Output encoding
        sb.append("&tk="); //Token authentication parameter
        sb.append(generateToken(text));
        return sb.toString();
    }

    public static String detectLanguage(String text) throws IOException {
        String urlText = generateURL("auto", "en", text);
        URL url = new URL(urlText); //Generates URL
        String rawData = urlToText(url);//Gets text from Google
        return findLanguage(rawData);
    }

    public static String translate(String text) throws IOException {
        return translate(Locale.getDefault().getLanguage(), text);
    }

    public static String translate(String targetLanguage , String text) throws IOException {
        return translate("auto", targetLanguage, text);
    }

    public static String translate(String sourceLanguage , String targetLanguage , String text) throws IOException {
        String urlText = generateURL(sourceLanguage, targetLanguage, text);
        System.out.println(urlText);
        URL url = new URL(urlText);
        String rawData = urlToText(url);//Gets text from Google
        if (rawData == null) {
            return null;
        }
        String[] raw = rawData.split("\"");//Parses the JSON
        if (raw.length < 2) {
            return null;
        }
        return raw[1];//Returns the translation
    }

    private static String urlToText(URL url) throws IOException {
        URLConnection urlConn = url.openConnection(); //Open connection
        //Adding header for user agent is required. Otherwise, Google rejects the request
        urlConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:2.0) Gecko/20100101 Firefox/4.0");
        Reader r = new java.io.InputStreamReader(urlConn.getInputStream(), Charset.forName("UTF-8"));//Gets Data Converts to string
        StringBuilder buf = new StringBuilder();
        while (true) {//Reads String from buffer
            int ch = r.read();
            if (ch < 0)
                break;
            buf.append((char) ch);
        }
        return buf.toString();
    }

    private static String findLanguage(String rawData) {
        for (int i = 0; i + 5 < rawData.length(); i++) {
            boolean dashDetected = rawData.charAt(i + 4) == '-';
            if (rawData.charAt(i) == ',' && rawData.charAt(i + 1) == '"' && ( ( rawData.charAt(i + 4) == '"' && rawData.charAt(i + 5) == ',' ) || dashDetected )) {
                if (dashDetected) {
                    int lastQuote = rawData.substring(i + 2).indexOf('"');
                    if (lastQuote > 0)
                        return rawData.substring(i + 2, i + 2 + lastQuote);
                } else {
                    String possible = rawData.substring(i + 2, i + 4);
                    if (containsLettersOnly(possible)) {//Required due to Google's inconsistent formatting.
                        return possible;
                    }
                }
            }
        }
        return null;
    }

    private static boolean containsLettersOnly(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isLetter(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int[] TKK() {
        return new int[]{ 0x6337E , 0x217A58DC + 0x5AF91132 };
    }

    private static int shr32(int x , int bits) {
        if (x < 0) {
            long x_l = 0xffffffffl + x + 1;
            return (int) ( x_l >> bits );
        }
        return x >> bits;
    }

    private static int RL(int a , String b) {//I am not entirely sure what this magic does.
        for (int c = 0; c < b.length() - 2; c += 3) {
            int d = b.charAt(c + 2);
            d = d >= 65 ? d - 87 : d - 48;
            d = b.charAt(c + 1) == '+' ? shr32(a, d) : ( a << d );
            a = b.charAt(c) == '+' ? ( a + ( d & 0xFFFFFFFF ) ) : a ^ d;
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
                    if (0xD800 == ( g & 0xFC00 ) && f + 1 < text.length() && 0xDC00 == ( text.charAt(f + 1) & 0xFC00 )) {
                        g = 0x10000 + ( ( g & 0x3FF ) << 10 ) + ( text.charAt(++f) & 0x3FF );
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
            a_l = 0x80000000l + ( a_i & 0x7FFFFFFF );
        } else {
            a_l = a_i;
        }
        a_l %= Math.pow(10, 6);
        return String.format(Locale.US, "%d.%d", a_l, a_l ^ b);
    }
}