package app.controllers.panes;

import app.actions.DictionaryAction;
import app.controllers.elements.AlertController;
import app.dictionary.DictionaryManagement;
import app.dictionary.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchPaneController implements Initializable {

    @FXML
    private TextField input_search;
    @FXML
    private ListView<String> search_list_view;
    @FXML
    private AnchorPane right_content;
    private DictionaryAction dictionaryAction = new DictionaryAction();

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
        ArrayList<String> stringWords = dictionaryAction.getStringSearchs(spelling);
        search_list_view.getItems().setAll(stringWords);
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

    private void loadViewWord(String spelling, String explain) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../../../graphical/panes/view_word_pane.fxml"));
        VBox viewWordVBox;
        try {
            viewWordVBox = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Error load alert pane.");
            return;
        }
        right_content.getChildren().addAll(viewWordVBox);
//        AlertController controller = fxmlLoader.getController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadViewWord("Quang", "Quang BKL");
//        ArrayList<String> items = new ArrayList<>();
//        items.add("Hello");
//        items.add("I am Quang");
//        items.add("The Flash");
//        items.add("BKL");
//        search_list_view.getItems().setAll(items);
    }
}
