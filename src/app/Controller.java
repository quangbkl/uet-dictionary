package app;

import app.dictionary.DictionaryManagement;
import app.dictionary.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public class Controller {
    @FXML
    Button btn_nav_search, btn_nav_add, btn_nav_history, btn_nav_bookmark;
    @FXML
    TextField input_search;
    @FXML
    AnchorPane content_pane;
    DictionaryManagement dictionaryManagement = new DictionaryManagement();

    @FXML
    public void handleClickMicro(ActionEvent event) {
        System.out.println("Click button audio.");
        Media media = new Media("https://vnno-vn-6-tf-mp3-s1-zmp3.zadn.vn/c9ce5f658c21657f3c30/5337160813560919543?authen=exp=1538270327~acl=/c9ce5f658c21657f3c30/*~hmac=cc7988fad3244df676af342101afb11f&filename=Cang-Niu-Giu-Cang-De-Mat-Mr-Siro.mp3");
        MediaPlayer player = new MediaPlayer(media);
        player.play();
    }

    @FXML
    public void handleClickSidebar(ActionEvent event) {
        if (event.getSource() == btn_nav_search) {
            System.out.println("Search");
            content_pane.setVisible(true);
        } else if (event.getSource() == btn_nav_add) {
            System.out.println("Add");
        } else if (event.getSource() == btn_nav_history) {
            System.out.println("History");
        } else if (event.getSource() == btn_nav_bookmark) {
            System.out.println("Bookmark");
            content_pane.setVisible(false);
        }

        System.out.println("Click sidebar");
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
}
