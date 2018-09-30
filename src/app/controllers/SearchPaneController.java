package app.controllers;

import app.dictionary.DictionaryManagement;
import app.dictionary.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchPaneController implements Initializable {

    @FXML
    private TextField input_search;
    @FXML
    private AnchorPane content_pane;

    private DictionaryManagement dictionaryManagement = new DictionaryManagement();

    @FXML
    public void handleClickMicro(ActionEvent event) {
        System.out.println("Click button audio.");
        Media media = new Media("https://vnno-vn-6-tf-mp3-s1-zmp3.zadn.vn/c9ce5f658c21657f3c30/5337160813560919543?authen=exp=1538270327~acl=/c9ce5f658c21657f3c30/*~hmac=cc7988fad3244df676af342101afb11f&filename=Cang-Niu-Giu-Cang-De-Mat-Mr-Siro.mp3");
        MediaPlayer player = new MediaPlayer(media);
        player.play();
    }

    @FXML
    public void handleEnterInputSearch(ActionEvent event) {
        if (event.getSource() == input_search) {
            System.out.println("Enter input search");
        }
    }

    public void actionSearch(String spelling) {
        ArrayList<Word> words = dictionaryManagement.dictionarySearcher(spelling);
        for (Word word: words) {
            System.out.println(word.getSpelling());
        }
        System.out.println();
    }

    @FXML
    public void handleChangeInputSearch(KeyEvent event) {
        if (event.getSource() == input_search) {
            String searchText = input_search.getText();
            if (!searchText.isEmpty()) {
                this.actionSearch(searchText);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
