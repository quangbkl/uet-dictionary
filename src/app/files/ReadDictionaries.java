package app.files;

import app.dictionary.Word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadDictionaries {
    public ArrayList<Word> read() {
        String fileName = "src/app/files/dictionaries.txt";
        String line = null;
        String[] words;
        ArrayList<Word> result = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                words = line.split("\t");
                Word word = new Word(words[0], words[1]);
                result.add(word);
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
