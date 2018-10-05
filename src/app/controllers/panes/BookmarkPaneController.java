package app.controllers.panes;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class BookmarkPaneController implements Initializable {
    private ContainerController state;

    public void initData(ContainerController state) {
        this.state = state;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
