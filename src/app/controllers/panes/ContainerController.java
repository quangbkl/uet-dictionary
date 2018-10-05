package app.controllers.panes;

import app.actions.DictionaryAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContainerController implements Initializable {
    @FXML
    private Button btn_nav_search, btn_nav_add, btn_nav_history, btn_nav_bookmark;
    @FXML
    private AnchorPane content_pane;
    private AnchorPane anchorAddPane = null, anchorBookmarkPane = null, anchorHistoryPane = null, anchorSearchPane = null;
    private AddPaneController addPaneController;
    private BookmarkPaneController bookmarkPaneController;
    private HistoryPaneController historyPaneController;
    private SearchPaneController searchPaneController;
    private ViewWordPaneController viewWordPaneController;
    private DictionaryAction dictionaryAction = new DictionaryAction();

    public DictionaryAction getDictionaryAction() {
        return dictionaryAction;
    }

    void showHistoryPane() {
        content_pane.getChildren().setAll(anchorHistoryPane);
        historyPaneController.initData(this);
    }

    private void showAddPane() {
        content_pane.getChildren().setAll(anchorAddPane);
        addPaneController.initData(this);
    }

    private void showBookmarkPane() {
        content_pane.getChildren().setAll(anchorBookmarkPane);
        bookmarkPaneController.initData(this);
    }

    private void showSearchPane() {
        content_pane.getChildren().setAll(anchorSearchPane);
        searchPaneController.initData(this);
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
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../graphical/panes/search_pane.fxml"));
            anchorSearchPane = fxmlLoader.load();
            searchPaneController = fxmlLoader.getController();
        } catch (IOException e) {
            System.out.println("Error load search_pane.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../graphical/panes/add_pane.fxml"));
            anchorAddPane = fxmlLoader.load();
            addPaneController = fxmlLoader.getController();
        } catch (IOException e) {
            System.out.println("Error load add_pane pane.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../graphical/panes/bookmark_pane.fxml"));
            anchorBookmarkPane = fxmlLoader.load();
            bookmarkPaneController = fxmlLoader.getController();
        } catch (IOException e) {
            System.out.println("Error load bookmark_pane pane.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../graphical/panes/history_pane.fxml"));
            anchorHistoryPane = fxmlLoader.load();
            historyPaneController = fxmlLoader.getController();
        } catch (IOException e) {
            System.out.println("Error load history_pane pane.");
        }

        content_pane.getChildren().setAll(anchorSearchPane);
    }
}
