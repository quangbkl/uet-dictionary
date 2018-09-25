package graphical;

import app.dictionary.DictionaryManagement;
import app.dictionary.Word;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class DictionaryFrame {
    private JButton convertButton;
    private JTextField textSpelling;
    private JButton searchButton;
    private JLabel en;
    private JLabel vn;
    private JPanel content;
    private JList searchersList;
    DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public DictionaryFrame() {
        dictionaryManagement.insertFromFile();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello.");
            }
        });
        textSpelling.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String inputText = textSpelling.getText();
                ArrayList<Word> words = dictionaryManagement.dictionarySearcher(inputText);
                String[] listWords = new String[words.size()];

                for (int i = 0, length = words.size(); i < length; i++) {
                    listWords[i] = words.get(i).getSpelling();
                }

                searchersList.setListData(listWords);
            }
        });
    }

    public void showUIComponents() {
        JFrame frame = new JFrame("Dictionary Frame");
        frame.setContentPane(new DictionaryFrame().content);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
