package app.helpers;

import app.dictionary.base.Word;

import java.io.*;
import java.util.ArrayList;

public class IODictionaries {
    public ArrayList<Word> read() {
        return this.read("data/dictionaries.txt");
    }

    public ArrayList<Word> read(String path) {
//        String fileName = "src/data/dictionaries.txt";
        String line = null;
        String[] words;
        ArrayList<Word> result = new ArrayList<>();

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path), "UTF8");

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((line = bufferedReader.readLine()) != null) {
                words = line.split("\t");

                if (words.length >= 2) {
                    Word word = new Word(words[0], words[1]);
                    result.add(word);
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public void write(ArrayList<Word> words) {
        this.write(words, "data/save_dictionaries.txt");
    }

    public void write(ArrayList<Word> words, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            for (Word word : words) {
                bufferedWriter.write(word.getSpelling() + "\t" + word.getExplain());
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
