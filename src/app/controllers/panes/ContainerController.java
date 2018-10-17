package app.controllers.panes;

import app.actions.BookmarkAction;
import app.actions.DictionaryAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContainerController implements Initializable {
    private DictionaryAction dictionaryAction = new DictionaryAction();
    private BookmarkAction bookmarkAction = new BookmarkAction();

    @FXML
    private Button btn_nav_search, btn_nav_add, btn_nav_history, btn_nav_bookmark;
    @FXML
    private AnchorPane content_pane;

    private AnchorPane anchorAddPane = null, anchorBookmarkPane = null, anchorHistoryPane = null, anchorSearchPane = null;
    private AnchorPane currentPane;
    private AddPaneController addPaneController;
    private BookmarkPaneController bookmarkPaneController;
    private HistoryPaneController historyPaneController;
    private SearchPaneController searchPaneController;


    public DictionaryAction getDictionaryAction() {
        return dictionaryAction;
    }

    public BookmarkAction getBookmarkAction() {
        return bookmarkAction;
    }

    private void setContentPane(AnchorPane anchorPane) {
        this.content_pane.getChildren().setAll(anchorPane);
        this.currentPane = anchorPane;
    }

    public void showHistoryPane() {
        this.setContentPane(anchorHistoryPane);
        historyPaneController.initData(this);
        this.resetStyleNav();
        btn_nav_history.setStyle("-fx-background-color:  #394357;");
    }

    public void showAddPane() {
        this.setContentPane(anchorAddPane);
        addPaneController.initData(this);
        this.resetStyleNav();
        btn_nav_add.setStyle("-fx-background-color:  #394357;");
    }

    public void showBookmarkPane() {
        this.setContentPane(anchorBookmarkPane);
        bookmarkPaneController.initData(this);
        this.resetStyleNav();
        btn_nav_bookmark.setStyle("-fx-background-color:  #394357;");

    }

    public void showSearchPane() {
        this.setContentPane(anchorSearchPane);
        searchPaneController.initData(this);
        this.resetStyleNav();
        btn_nav_search.setStyle("-fx-background-color:  #394357;");
    }

    public void resetStyleNav() {
        btn_nav_search.setStyle(null);
        btn_nav_add.setStyle(null);
        btn_nav_history.setStyle(null);
        btn_nav_bookmark.setStyle(null);
    }

    public void reset() {
        // TODO: relaod all pane.
        searchPaneController.reset();
        bookmarkPaneController.reset();
    }

    public void reloadBookmark() {
        bookmarkPaneController.reload();
    }

    public void resetBookmark() {
        bookmarkPaneController.reset();
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
            searchPaneController.initData(this);
        } catch (IOException e) {
            System.out.println("Error load search_pane.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../graphical/panes/add_pane.fxml"));
            anchorAddPane = fxmlLoader.load();
            addPaneController = fxmlLoader.getController();
            addPaneController.initData(this);
        } catch (IOException e) {
            System.out.println("Error load add_pane pane.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../graphical/panes/bookmark_pane.fxml"));
            anchorBookmarkPane = fxmlLoader.load();
            bookmarkPaneController = fxmlLoader.getController();
            bookmarkPaneController.initData(this);
        } catch (IOException e) {
            System.out.println("Error load bookmark_pane pane.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../../graphical/panes/history_pane.fxml"));
            anchorHistoryPane = fxmlLoader.load();
            historyPaneController = fxmlLoader.getController();
            historyPaneController.initData(this);
        } catch (IOException e) {
            System.out.println("Error load history_pane pane.");
        }

        this.showSearchPane();
    }
}
