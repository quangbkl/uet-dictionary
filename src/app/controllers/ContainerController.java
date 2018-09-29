package app.controllers;

import app.dictionary.DictionaryManagement;
import app.dictionary.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContainerController implements Initializable {
    @FXML
    private Button btn_nav_search, btn_nav_add, btn_nav_history, btn_nav_bookmark;
    @FXML
    private AnchorPane content_pane;
    private AnchorPane anchorAddPane = null, anchorBookmarkPane = null, anchorHistoryPane = null, anchorSearchPane = null;

    private void showHistoryPane() {
        content_pane.getChildren().setAll(anchorHistoryPane);
    }

    private void showAddPane() {
        content_pane.getChildren().setAll(anchorAddPane);
    }

    private void showBookmarkPane() {
        content_pane.getChildren().setAll(anchorBookmarkPane);
    }

    private void showSearchPane() {
        content_pane.getChildren().setAll(anchorSearchPane);
    }

    @FXML
    public void handleClickSidebar(ActionEvent event) {
        if (event.getSource() == btn_nav_search) {
            showSearchPane();
        } else if (event.getSource() == btn_nav_add) {
            showAddPane();
        } else if (event.getSource() == btn_nav_history) {
            showHistoryPane();
        } else if (event.getSource() == btn_nav_bookmark) {
            showBookmarkPane();
        }

        System.out.println("Click sidebar");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            anchorSearchPane = FXMLLoader.load(getClass().getResource("../../graphical/search_pane.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            anchorAddPane = FXMLLoader.load(getClass().getResource("../../graphical/add_pane.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            anchorBookmarkPane = FXMLLoader.load(getClass().getResource("../../graphical/bookmark_pane.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            anchorHistoryPane = FXMLLoader.load(getClass().getResource("../../graphical/history_pane.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        content_pane.getChildren().setAll(anchorSearchPane);
    }
}
