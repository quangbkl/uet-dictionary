package services;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class SearchToHTML {
    private JSONParser jsonParser = new JSONParser();

    public ArrayList<String> parserHTML(String textParser) throws ParseException {
        ArrayList<String> result = new ArrayList<>();

        JSONArray jsonArray = (JSONArray) jsonParser.parse(textParser);
        JSONArray arraySearchs = (JSONArray) jsonArray.get(1);

        for (int i = 0, length = arraySearchs.size(); i < length; i++) {
            String word = (String) ((JSONArray) arraySearchs.get(i)).get(0);
            result.add(word);
        }

        return result;
    }
}
