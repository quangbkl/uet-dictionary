package services;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StringToHTML {
    private JSONParser jsonParser = new JSONParser();

    public String parserHTML(String textParser) throws ParseException {
        String jsonStringSpelling;
        String jsonStringExplain;
        String jsonStringPronounce = null;
        JSONArray jsonArray = (JSONArray) jsonParser.parse(textParser);

        JSONArray jsonArrayWord = (JSONArray) jsonArray.get(0);
        jsonStringExplain = (String) ((JSONArray) jsonArrayWord.get(0)).get(0);
        jsonStringSpelling = (String) ((JSONArray) jsonArrayWord.get(0)).get(1);

        if (jsonArrayWord.size() > 1) {
            JSONArray jsonMoreWord = (JSONArray) jsonArrayWord.get(1);
            if (jsonMoreWord.get(3) != null)
                jsonStringPronounce = (String) jsonMoreWord.get(3);
        }

        if (jsonArray.get(1) == null) {
            String newResult = "<i>" + jsonStringSpelling + " /" + jsonStringPronounce + "/</i>" +
                    "<ul><li><font color='#cc0000'><b>" + jsonStringExplain + "</b></font></li><ul>";
            return newResult;
        }
        String result = "<i>" + jsonStringSpelling + " /" + jsonStringPronounce + "/</i><br /><ul>";
        JSONArray jsonArrayExplain = (JSONArray) jsonArray.get(1);
        for (int i = 0, length = jsonArrayExplain.size(); i < length; i++) {
            result += partsOfSpeech((JSONArray) jsonArrayExplain.get(i));
        }

        result += "</ul>";
        return result;
    }

    public String arrayToString(JSONArray jsonArray) {
        String result = "";
        for (int i = 0, length = jsonArray.size(); i < length; i++) {
            result += jsonArray.get(i) + ", ";
        }

        return result.substring(0, result.length() - 2);
    }

    public String getWord(JSONArray jsonArray) {
        String spelling = (String) jsonArray.get(0);
        JSONArray explain = (JSONArray) jsonArray.get(1);
        String result = "<ul><li><font color='#cc0000'><b>" + spelling + "</b></font><ul><li>" +
                arrayToString(explain) +
                "</li></ul></li></ul>";
        return result;
    }

    private String partsOfSpeech(JSONArray jsonParsers) {
        String result = "<li><b><i>";
        String partOfSpeech = (String) jsonParsers.get(0);
        result += partOfSpeech;
        result += "</i></b>";
        JSONArray jsonArrayWords = (JSONArray) jsonParsers.get(2);

        for (int i = 0, length = jsonArrayWords.size(); i < length; i++) {
            result += getWord((JSONArray) jsonArrayWords.get(i));
        }

        result += "</li>";
        return result;
    }
}
