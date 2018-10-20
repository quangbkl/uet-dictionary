package app.controllers.panes;

import app.dictionary.base.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    protected ContainerController state;

    @FXML
    protected TextField input_search;
    @FXML
    protected ListView<String> search_list_view;
    @FXML
    protected AnchorPane right_content;
    protected ViewWordPaneController controllerViewWord;

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

    @FXML
    public void handleSelectItemListView(MouseEvent event) {
        String spelling = search_list_view.getSelectionModel().getSelectedItem();
        if (spelling == null) return;
        input_search.setText(spelling);
        actionSearch(spelling);
    }

    public void actionSearch(String spelling) {
        ArrayList<String> stringWords = this.state.getDictionaryAction().getStringSearchs(spelling);
        search_list_view.getItems().setAll(stringWords);

        Word word = state.getDictionaryAction().dictionaryLookup(spelling);
        if (word != null)
            controllerViewWord.initData(this.state, word.getSpelling(), word.getExplain());
    }

    @FXML
    public void handleChangeInputSearch(KeyEvent event) {
        if (event.getSource() == input_search) {
            String searchText = input_search.getText();
            if (!searchText.isEmpty()) {
                actionSearch(searchText);
            } else {
                search_list_view.getItems().clear();
            }
        }
    }

    public void reset() {
        input_search.setText("");
        search_list_view.getItems().clear();
        controllerViewWord.initData(this.state, "", "");
    }

    public void reload() {
        if (state == null) return;

        String searchText = input_search.getText();
        if (!searchText.isEmpty()) {
            actionSearch(searchText);
        } else {
            search_list_view.getItems().clear();
        }

        controllerViewWord.reload();
    }

    protected void loadViewWord(String spelling, String explain) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../../../graphical/panes/view_word_pane.fxml"));
        VBox viewWordVBox;
        try {
            viewWordVBox = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Error load view word pane.");
            return;
        }
        right_content.getChildren().addAll(viewWordVBox);
        controllerViewWord = fxmlLoader.getController();
        controllerViewWord.initData(this.state, spelling, explain);
    }

    public void initData(ContainerController state) {
        this.state = state;
        if (controllerViewWord == null) {
            loadViewWord("", "");
        }

        this.reload();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        loadViewWord("Spelling", "Explain");
    }
}